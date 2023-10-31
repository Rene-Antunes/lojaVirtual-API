package com.lojavirtual.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lojavirtual.api.assembler.PermissaoDtoAssembler;
import com.lojavirtual.api.assembler.disassembler.PermissaoInputDisassembler;
import com.lojavirtual.api.modelDto.PermissaoDto;
import com.lojavirtual.api.modelDto.input.PermissaoDtoInput;
import com.lojavirtual.domain.model.Permissao;
import com.lojavirtual.domain.repository.PermissaoRepository;
import com.lojavirtual.domain.service.CadastroPermissaoService;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;import lombok.val;

@RestController
@RequestMapping(value = "permissoes")
public class PermissaoController {
	
	@Autowired
	private PermissaoRepository repository;
	
	@Autowired
	public PermissaoDtoAssembler assembler;
	
	@Autowired
	public CadastroPermissaoService service;
	
	@Autowired
	private PermissaoInputDisassembler disassembler;
	
	@GetMapping
	public List<PermissaoDto> listar (){
		
		List<Permissao> permissoes = repository.findAll();
		
		return assembler.toCollectionDto(permissoes);
		
	}
	
	
	@GetMapping("/{permissaoId}")
	public PermissaoDto buscar(@PathVariable Long permissaoId) {
		Permissao permissao = service.buscarOuFalhar(permissaoId);
		
		return assembler.toDto(permissao);
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PermissaoDto adicionar(@RequestBody @Valid PermissaoDtoInput permissaoInput) {
		
		Permissao permissao = disassembler.toDomainObject(permissaoInput);
		service.salvar(permissao);
		return assembler.toDto(permissao);
		
	}
	
	@PutMapping("/{permissaoId}")
	public PermissaoDto alterar(@PathVariable Long permissaoId  ,@RequestBody @Valid PermissaoDtoInput PermissaoInput) {
		
		Permissao permissao = service.buscarOuFalhar(permissaoId);
		disassembler.copyToDomainObject(PermissaoInput, permissao);
		service.salvar(permissao);
		
		return assembler.toDto(permissao);
	}
	
	@DeleteMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long permissaoId ) {
		service.excluir(permissaoId);
	}
}
