package com.lojavirtual.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lojavirtual.api.assembler.PermissaoDtoAssembler;
import com.lojavirtual.api.modelDto.PermissaoDto;
import com.lojavirtual.domain.model.Grupo;
import com.lojavirtual.domain.service.CadastroGrupoService;

@RestController
@RequestMapping(value = "/grupos/{grupoId}/permissoes")
public class GrupoPermissaoController {
	
	@Autowired
	private CadastroGrupoService grupoService;
	@Autowired
	private PermissaoDtoAssembler permissaoAssembler;

	@GetMapping
	public List<PermissaoDto> listar(@PathVariable Long grupoId){
		Grupo grupo = grupoService.buscarOuFalhar(grupoId);
		return permissaoAssembler.toCollectionDto(grupo.getPermissoes());
	}
	
	@PutMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		grupoService.associarPermissao(grupoId, permissaoId);
	}
	
	@DeleteMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		grupoService.desassociarPermissao(grupoId, permissaoId);
	}
	
}
