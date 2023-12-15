package com.lojavirtual.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lojavirtual.api.assembler.GrupoDtoAssembler;
import com.lojavirtual.api.assembler.disassembler.GrupoInputDisassembler;
import com.lojavirtual.api.modelDto.GrupoDto;
import com.lojavirtual.api.modelDto.input.GrupoDtoInput;
import com.lojavirtual.domain.model.Grupo;
import com.lojavirtual.domain.repository.GrupoRepository;
import com.lojavirtual.domain.service.CadastroGrupoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/grupos")
public class GrupoController {
	
	
	@Autowired
	private GrupoRepository repository;
	
	@Autowired
	private GrupoDtoAssembler assembler;
	
	@Autowired
	private GrupoInputDisassembler disassembler;
	
	@Autowired
	private CadastroGrupoService service;
	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<GrupoDto> listar(){
		List<Grupo> grupos = repository.findAll();
		return assembler.toDtoCollection(grupos);	
	}
	
	@GetMapping(value = "/{grupoId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public GrupoDto buscar(@PathVariable Long grupoId) {
		Grupo grupo = service.buscarOuFalhar(grupoId);
		return assembler.toDto(grupo);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public GrupoDto adicionar(@RequestBody @Valid GrupoDtoInput grupoDtoInput) {
		Grupo grupo = disassembler.toDomainObject(grupoDtoInput);
		service.salvar(grupo);
		return assembler.toDto(grupo);
	}
	
	@PutMapping(value = "/{grupoId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public GrupoDto alterar(@PathVariable Long grupoId, @RequestBody @Valid GrupoDtoInput grupoDtoInput) {
		Grupo novoGrupo = service.buscarOuFalhar(grupoId);
		disassembler.copyToDomainObject(grupoDtoInput, novoGrupo);
		service.salvar(novoGrupo);
		return assembler.toDto(novoGrupo);
	}
	
	@DeleteMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long grupoId) {
		service.exlcuir(grupoId);
		
		//TODO altenticar usuário para permitir exclusão
		
	}

}
