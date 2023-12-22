package com.lojavirtual.api.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lojavirtual.api.assembler.ProdutoDtoAssembler;
import com.lojavirtual.api.assembler.disassembler.ProdutoInputDisassembler;
import com.lojavirtual.api.modelDto.ProdutoDto;
import com.lojavirtual.api.modelDto.input.ProdutoDtoInput;
import com.lojavirtual.domain.filter.ProdutoFilter;
import com.lojavirtual.domain.model.Produto;
import com.lojavirtual.domain.repository.ProdutoRepository;
import com.lojavirtual.domain.service.CadastroProdutoService;
import com.lojavirtual.infraInstructure.spec.ProdutoSpec;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private CadastroProdutoService service;
	
	@Autowired
	private ProdutoDtoAssembler assembler;
	
	@Autowired
	private ProdutoInputDisassembler disassembler;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<ProdutoDto>> listar(ProdutoFilter produtoFilter, Pageable pageable){
		
		Page<Produto> produtosPage = repository.findAll(ProdutoSpec.produtoFilter(produtoFilter), pageable);

		List<ProdutoDto> produtoDtos = assembler.toDtoCollection(produtosPage.getContent());
		
		Page<ProdutoDto> produtoDtoPage = new PageImpl<>(produtoDtos, pageable,produtosPage.getTotalElements());
		
		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES))
				.body(produtoDtoPage);
	}

	
	@GetMapping(value = "/{produtoId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProdutoDto buscar(@PathVariable Long produtoId) {
		Produto protuto = service.buscarOuFalhar(produtoId);
		return assembler.toDto(protuto);
	}
	
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoDto adicionar(@RequestBody @Valid ProdutoDtoInput produtoInput) {
	
		Produto produto = disassembler.toDomainObject(produtoInput);
		service.salvar(produto);
		
		return assembler.toDto(produto);
	}
	
	@PutMapping(value = "/{produtoId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProdutoDto alterar(@PathVariable Long produtoId 
			,@RequestBody @Valid ProdutoDtoInput produtoInput) {
		
		 Produto produto = service.buscarOuFalhar(produtoId);
		 disassembler.copyToDomainObject(produtoInput, produto);
		 service.salvar(produto);
		 
		 return assembler.toDto(produto);
	}
	
	@DeleteMapping("/{produtoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long produtoId) {
		service.excluir(produtoId);
	}
	
	@PutMapping("/{produtoId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atvivar(@PathVariable Long produtoId) {
		service.ativar(produtoId);
	}
	
	@DeleteMapping("{produtoId}/inativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativar(@PathVariable Long produtoId) {
		service.inativar(produtoId);
	}
	

	
}
