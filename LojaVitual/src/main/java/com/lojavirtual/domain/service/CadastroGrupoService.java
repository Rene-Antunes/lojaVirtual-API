package com.lojavirtual.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lojavirtual.domain.exception.GrupoNaoEncontradoException;
import com.lojavirtual.domain.model.Grupo;
import com.lojavirtual.domain.model.Permissao;
import com.lojavirtual.domain.repository.GrupoRepository;

import jakarta.transaction.Transactional;

@Service
public class CadastroGrupoService {
	
	@Autowired
	private GrupoRepository repository;
	
	@Autowired
	private CadastroPermissaoService permissaoService;
	
	@Transactional
	public Grupo salvar(Grupo grupo) {
		return repository.save(grupo);
	}
	
	@Transactional
	public void exlcuir(Long grupoId) {
		try {
			buscarOuFalhar(grupoId);
			repository.deleteById(grupoId);
			
		} catch (GrupoNaoEncontradoException e) {
			throw new GrupoNaoEncontradoException("Grupo não foi encontrado, portanto não pode ser deletado");
		}
	}
	
	public Grupo buscarOuFalhar(Long grupoId) {
		return repository.findById(grupoId)
				.orElseThrow(()-> new GrupoNaoEncontradoException(grupoId));
	}
	
	@Transactional
	public void associarPermissao(Long grupoId, Long permissaoId) {
		Grupo grupo = buscarOuFalhar(grupoId);
		Permissao permissao = permissaoService.buscarOuFalhar(permissaoId);
		
		grupo.adicionarPermissao(permissao);
	}
	
	@Transactional
	public void desassociarPermissao(Long grupoId, Long permissaoId) {
		Grupo grupo = buscarOuFalhar(grupoId);
		Permissao permissao = permissaoService.buscarOuFalhar(permissaoId);
		
		grupo.removerPermissao(permissao);
	}
}
