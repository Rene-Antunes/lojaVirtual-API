package com.lojavirtual.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lojavirtual.domain.exception.EntidadeNaoEncontradaException;
import com.lojavirtual.domain.exception.FormaPagamentoNaoEncontradoException;
import com.lojavirtual.domain.exception.ProdutoNaoEncontradoException;
import com.lojavirtual.domain.exception.UsuarioNaoEncontradoException;
import com.lojavirtual.domain.model.FormaPagamento;
import com.lojavirtual.domain.model.Produto;
import com.lojavirtual.domain.model.Usuario;
import com.lojavirtual.domain.repository.FormaPagamentoRepository;
import com.lojavirtual.domain.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

@Service
public class CadastroFormaPagamentoService {
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	
	@Transactional
	public FormaPagamento salvar(FormaPagamento formaPagamento) {
		
		return formaPagamentoRepository.save(formaPagamento);
	}
	
	
	@Transactional
	public void excluir (Long formaPagamentoId) {
		try {
			formaPagamentoRepository.deleteById(formaPagamentoId);
		} catch (EntidadeNaoEncontradaException e) {
			throw new FormaPagamentoNaoEncontradoException(formaPagamentoId);
		}
		
	}
	
	public FormaPagamento buscarOuFalhar(Long formaPagamentoId) {
		return formaPagamentoRepository.findById(formaPagamentoId)
				.orElseThrow(() -> new FormaPagamentoNaoEncontradoException(formaPagamentoId));
		
	}
	
}
