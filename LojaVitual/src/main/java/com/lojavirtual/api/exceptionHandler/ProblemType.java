package com.lojavirtual.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"),
	ERRO_DE_SISTEMA("/erro-de-sistema", "erro de sistema"),
	PARAMETRO_INVALIDO("/parametro-invalido", "Parametro está inválido"),
	MENSAGEM_INCOMPREESIVEL("/mensagem-incompreesivel", "Mensagem incompreensivel"),
	RECURSO_NAO_ENCONTRADA("/recurso-nao-encontrado", "Recurso não encontrado"),
	ERRO_NEGOCIO("/erro-negocio", "Violação à regra de negócio"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensível", "Mensagem incompreensível");
	
	
	
	private String title;
	private String uri;
	
	
	private ProblemType(String path, String title) {
		this.uri = "http://braodasjoias.com.br" + path;
		this.title = title;
	}
}
