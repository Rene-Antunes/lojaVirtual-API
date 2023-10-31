package com.lojavirtual.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lojavirtual.domain.exception.EntidadeEmUsoException;
import com.lojavirtual.domain.exception.EntidadeNaoEncontradaException;
import com.lojavirtual.domain.exception.ProdutoNaoEncontradoException;
import com.lojavirtual.domain.exception.UsuarioNaoEncontradoException;
import com.lojavirtual.domain.model.Produto;
import com.lojavirtual.domain.model.Usuario;
import com.lojavirtual.domain.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

@Service
public class CadastroProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	
	@Transactional
	public Produto salvar(Produto produto) {
		
		return produtoRepository.save(produto);
	}
	
	@Transactional
	public void ativar(Long produtoId) {
		Produto produto = buscarOuFalhar(produtoId);
		
		produto.ativar();
	}
	
	@Transactional
	public void inativar(Long produtoId) {
		Produto produto = buscarOuFalhar(produtoId);
		produto.inativar();
	}
	
	@Transactional
	public void excluir (Long produtoId) {
		Produto produto = buscarOuFalhar(produtoId);
		
		if (produto.getAtivo() == true) {
			throw new EntidadeEmUsoException("Produto esta ativo e não pode ser deletado");
		}
		
		try {
			produtoRepository.deleteById(produtoId);
		} catch (EntidadeNaoEncontradaException e) {
			throw new ProdutoNaoEncontradoException(produtoId);
		}
		
	}
	
	public Produto buscarOuFalhar(Long propdutoId) {
		return produtoRepository.findById(propdutoId)
				.orElseThrow(() -> new ProdutoNaoEncontradoException(propdutoId));
		
	}
	
}
