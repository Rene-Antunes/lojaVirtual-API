package com.lojavirtual.domain.exception;

public class EntidadeNaoEncontradaException extends NegocioException{

	private static final long serialVersionUID = 1L;
	
	
	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);

	}
	public EntidadeNaoEncontradaException(String mensagem, Throwable causa) {
		super(mensagem, causa);

	}

}
