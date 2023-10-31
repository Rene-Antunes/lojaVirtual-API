package com.lojavirtual.domain.service;

import java.io.InputStream;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

public interface ArmazenaFotoService {
	
	void Armazena(NovaFoto novaFoto);
	void remover(String nomeArquivo);
	
	InputStream recuperar(String nomeArquivo);
	
	
	default String gerarNomeArquivo(String NomeOrigem) {
		return UUID.randomUUID().toString() + "_" + NomeOrigem;
	}
	
	
	default void substituir(String arquivoFotoAnterior, NovaFoto novaFoto) {
		this.Armazena(novaFoto);
		
		if(arquivoFotoAnterior != null) {
			this.remover(arquivoFotoAnterior);
		}
	}
	
	@Builder
	@Getter
	class NovaFoto{
		private String nomeArquivo;
		private InputStream inputStream;
	}
	
	
}


