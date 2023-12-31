package com.lojavirtual.domain.exception;

public class CidadeNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public CidadeNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public CidadeNaoEncontradoException(Long produtoId) {
		this(String.format("Não existe um cadastro de cidade com código %d", produtoId));
	}
	
	
}
