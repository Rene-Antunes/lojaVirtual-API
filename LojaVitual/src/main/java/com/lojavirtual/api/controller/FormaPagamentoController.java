package com.lojavirtual.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lojavirtual.api.assembler.FormaPagamentoDtoAssembler;
import com.lojavirtual.api.assembler.disassembler.FormaPagamentoInputDisassembler;
import com.lojavirtual.api.modelDto.FormaPagamentoDto;
import com.lojavirtual.api.modelDto.input.FormaPagamentoDtoInput;
import com.lojavirtual.domain.model.FormaPagamento;
import com.lojavirtual.domain.repository.FormaPagamentoRepository;
import com.lojavirtual.domain.service.CadastroFormaPagamentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/formasPagamento")
public class FormaPagamentoController {
	
	@Autowired
	private FormaPagamentoRepository repository;
	
	@Autowired
	private FormaPagamentoDtoAssembler assembler;
	
	@Autowired
	private CadastroFormaPagamentoService service;
	
	@Autowired
	private FormaPagamentoInputDisassembler disassembler;
	
	@GetMapping
	public List<FormaPagamentoDto> listar(){
		List<FormaPagamento> formasPagamento = repository.findAll();
		return assembler.toCollectionDto(formasPagamento);
	}
	
	@GetMapping("/{formaPagamentoId}")
	public FormaPagamentoDto buscar(@PathVariable Long formaPagamentoId) {
		FormaPagamento formaPagamento = service.buscarOuFalhar(formaPagamentoId);
		return assembler.toDto(formaPagamento);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamentoDto adicionar(@RequestBody @Valid FormaPagamentoDtoInput formaPagamentoInput) {
		
		FormaPagamento formaPagamento =	disassembler.toDomainObject(formaPagamentoInput);
		service.salvar(formaPagamento);
		
		return assembler.toDto(formaPagamento);
	}
	
	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long formaPagamentoId) {
		service.excluir(formaPagamentoId);
	}

}
