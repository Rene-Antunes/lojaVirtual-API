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

import com.lojavirtual.api.assembler.UsuarioDtoAssembler;
import com.lojavirtual.api.assembler.disassembler.UsuarioInputDisassembler;
import com.lojavirtual.api.modelDto.UsuarioDto;
import com.lojavirtual.api.modelDto.input.SenhaDtoInput;
import com.lojavirtual.api.modelDto.input.UsuarioComSenhaDtoInput;
import com.lojavirtual.api.modelDto.input.UsuarioDtoInput;
import com.lojavirtual.domain.model.Usuario;
import com.lojavirtual.domain.repository.UsuarioRepository;
import com.lojavirtual.domain.service.CadastroUsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value =  "usuarios")
public class UsuarioAdmController {

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private UsuarioDtoAssembler assembler;
	
	@Autowired
	private UsuarioInputDisassembler disassembler;
	
	@Autowired
	private CadastroUsuarioService service;
	
	@GetMapping
	public List<UsuarioDto> listar(){
		List<Usuario> usuarios = repository.findAll();
		return assembler.toDtoCollection(usuarios);
	}
	
	@GetMapping("/{usuarioId}")
	public UsuarioDto buscar(@PathVariable Long usuarioId) {
		Usuario usuario =	service.buscarOuFalhar(usuarioId);
		
		return assembler.toDto(usuario);
	
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioDto adicionar(@RequestBody @Valid UsuarioComSenhaDtoInput usuarioAdmDtoInput) {
		Usuario usuario = disassembler.toDomainObject(usuarioAdmDtoInput);
		usuario = service.salvar(usuario);
		
		return 	assembler.toDto(usuario);
		 
	}
	
	@PutMapping("/{usuarioId}")
	public UsuarioDto alterar(@PathVariable Long usuarioId,
		@RequestBody @Valid UsuarioDtoInput usuarioInput) {
		
		Usuario usuario = service.buscarOuFalhar(usuarioId);
		disassembler.copyToDomainObject(usuarioInput, usuario);
		service.salvar(usuario);
		return assembler.toDto(usuario);
		
	}
	
	@PutMapping("/{usuarioId}/senha")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaDtoInput senha) {
		service.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
	}
	
	
	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long usuarioId) {
		service.excluir(usuarioId);
		
		//TODO altenticar usuário para permitir exclusão
		
	}
	
	
}
