package com.lojavirtual.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lojavirtual.domain.exception.EntidadeNaoEncontradaException;
import com.lojavirtual.domain.exception.PermissaoNaoEncontradoException;
import com.lojavirtual.domain.exception.ProdutoNaoEncontradoException;
import com.lojavirtual.domain.exception.UsuarioNaoEncontradoException;
import com.lojavirtual.domain.model.Permissao;
import com.lojavirtual.domain.model.Produto;
import com.lojavirtual.domain.model.Usuario;
import com.lojavirtual.domain.repository.PermissaoRepository;
import com.lojavirtual.domain.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

@Service
public class CadastroPermissaoService {
	
	@Autowired
	private PermissaoRepository permissaoRepository;

	
	@Transactional
	public Permissao salvar(Permissao permissao) {
		
		return permissaoRepository.save(permissao);
	}
	
	
	@Transactional
	public void excluir (Long permissaoId) {
		try {
			permissaoRepository.deleteById(permissaoId);
		} catch (EntidadeNaoEncontradaException e) {
			throw new PermissaoNaoEncontradoException(permissaoId);
		}
		
	}
	
	public Permissao buscarOuFalhar(Long permissaoId) {
		return permissaoRepository.findById(permissaoId)
				.orElseThrow(() -> new PermissaoNaoEncontradoException(permissaoId));
		
	}
	
}
