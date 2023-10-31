package com.lojavirtual.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.lojavirtual.api.assembler.PedidoDtoAssembler;
import com.lojavirtual.api.assembler.PedidoDtoResumoAssembler;
import com.lojavirtual.api.assembler.PedidoDtoResumoListaAssembler;
import com.lojavirtual.api.assembler.disassembler.PedidoInputDisassembler;
import com.lojavirtual.api.modelDto.PedidoDtoResumo;
import com.lojavirtual.api.modelDto.PedidoDtoResumoLista;
import com.lojavirtual.api.modelDto.input.PedidoDtoInput;
import com.lojavirtual.domain.exception.EntidadeNaoEncontradaException;
import com.lojavirtual.domain.exception.NegocioException;
import com.lojavirtual.domain.model.Pedido;
import com.lojavirtual.domain.repository.PedidoRepository;
import com.lojavirtual.domain.repository.filter.PedidoFilter;
import com.lojavirtual.domain.service.EmissaoPedidoService;
import com.lojavirtual.infraInstructure.spec.PedidoSpecs;

import io.micrometer.common.util.StringUtils;
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
	
	@Autowired
	private PedidoDtoResumoListaAssembler assemblerLista;
	
	
	@GetMapping("/pesquisar")
	public Page<PedidoDtoResumo> pesquisar(PedidoFilter pedidoFilter, @PageableDefault(size=10) Pageable pageable){
		
		Page<Pedido> pedidosPage = repository.findAll(PedidoSpecs.usandoFiltro(pedidoFilter), pageable);
		
		List<PedidoDtoResumo> pedidoDtos = assemblerResumo.toCollectionDto(pedidosPage.getContent());
		
		Page<PedidoDtoResumo> pedidoDtoPage = new PageImpl<>(pedidoDtos, pageable, pedidosPage.getTotalElements());
	
		return pedidoDtoPage;
		
	}
	
	@GetMapping
	public MappingJacksonValue listar(@RequestParam(required = false) String campos, @PageableDefault(size=10)Pageable pageable){
		
		Page<Pedido> pedidosPage = repository.findAll(pageable);
		
		List<PedidoDtoResumoLista> pedidosDtos = assemblerLista.toCollectionDto(pedidosPage.getContent());
		
		Page<PedidoDtoResumoLista> pedidosDtoPage = new PageImpl<>(pedidosDtos, pageable, pedidosPage.getTotalElements());
		
		MappingJacksonValue pedidosWrapper = new MappingJacksonValue(pedidosDtoPage);
		
		SimpleFilterProvider filterProvider = new SimpleFilterProvider();
		filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.serializeAll());
		
		if(StringUtils.isNotBlank(campos)) {
			filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.filterOutAllExcept(campos.split(",")));
			
		}
		
		pedidosWrapper.setFilters(filterProvider);
		
		return pedidosWrapper;
		
	} 

	@GetMapping("/{codigoPedido}")
	public PedidoDtoResumo buscar(@PathVariable String codigoPedido) {
		
		Pedido pedido = service.buscarOuFalhar(codigoPedido);
		return assemblerResumo.toDto(pedido);
		
	}
	
	@PostMapping
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
	
		
	
//	private Pageable traduzirPageable(Pageable pegeable) {
//
//		var mapeamento = ImmutableMap.of(
//				"codigo", "codigo",
//				"cliente.nome", "cliente.nome",
//				"subTotal", "subTotal",
//				"valorFrete", "valorFrete",
//				"valorTotal", "valorTotal"
//				);
//		
//		return PageableTranslator.translate(pegeable, mapeamento);
//	}
	
	
}
