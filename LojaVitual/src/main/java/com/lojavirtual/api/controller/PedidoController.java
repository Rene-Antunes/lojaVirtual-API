package com.lojavirtual.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;
import com.lojavirtual.api.assembler.PedidoDtoAssembler;
import com.lojavirtual.api.assembler.PedidoDtoResumoAssembler;
import com.lojavirtual.api.assembler.disassembler.PedidoInputDisassembler;
import com.lojavirtual.api.modelDto.PedidoDtoResumo;
import com.lojavirtual.api.modelDto.input.PedidoDtoInput;
import com.lojavirtual.core.data.PageableTranslator;
import com.lojavirtual.domain.exception.EntidadeNaoEncontradaException;
import com.lojavirtual.domain.exception.NegocioException;
import com.lojavirtual.domain.filter.PedidoFilter;
import com.lojavirtual.domain.model.Pedido;
import com.lojavirtual.domain.repository.PedidoRepository;
import com.lojavirtual.domain.service.EmissaoPedidoService;
import com.lojavirtual.infraInstructure.spec.PedidoSpecs;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "pedidos")
public class PedidoController {
	
	@Autowired
	public PedidoRepository repository;
	
	@Autowired
	public PedidoDtoAssembler assembler;
	
	@Autowired
	public EmissaoPedidoService service;
	
	@Autowired
	private PedidoInputDisassembler disassembler;
	
	@Autowired
	private PedidoDtoResumoAssembler assemblerResumo;
	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<PedidoDtoResumo> pesquisar(PedidoFilter pedidoFilter, @PageableDefault(size=10) Pageable pageable){
		
		pageable = traduzirPageable(pageable);
		
		Page<Pedido> pedidosPage = repository.findAll(PedidoSpecs.pedidoFiltro(pedidoFilter), pageable);
		
		List<PedidoDtoResumo> pedidoDtos = assemblerResumo.toCollectionDto(pedidosPage.getContent());
		
		Page<PedidoDtoResumo> pedidoDtoPage = new PageImpl<>(pedidoDtos, pageable, pedidosPage.getTotalElements());
	
		return pedidoDtoPage;
		
	}
	
	
	@GetMapping(value = "/{codigoPedido}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PedidoDtoResumo buscar(@PathVariable String codigoPedido) {
		
		Pedido pedido = service.buscarOuFalhar(codigoPedido);
		return assemblerResumo.toDto(pedido);
		
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoDtoResumo adicionar(@RequestBody @Valid PedidoDtoInput pedidoInput) {
		
		try {
			Pedido pedido = disassembler.toDomainObject(pedidoInput);
			service.emitir(pedido);
			return assemblerResumo.toDto(pedido);
			
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
		
	
		
	}
	
		
	
	private Pageable traduzirPageable(Pageable pegeable) {

		var mapeamento = ImmutableMap.of(
				"codigo", "codigo",
				"nome", "cliente.nome",
				"subTotal", "subTotal",
				"valorFrete", "valorFrete",
				"valorTotal", "valorTotal"
				);
		
		return PageableTranslator.translate(pegeable, mapeamento);
	}
	
	
}
