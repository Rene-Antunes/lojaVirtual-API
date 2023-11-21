package com.lojavirtual.domain.exception;

public class FotoProdutoNaoEncontradaException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;


	public FotoProdutoNaoEncontradaException(String message) {
		super(message);
	}
	
	public FotoProdutoNaoEncontradaException(Long produtoId) {
		this(String.format("Não existe um cadastro de foto do produto com código %d", produtoId));
	
	}
	
	
}
