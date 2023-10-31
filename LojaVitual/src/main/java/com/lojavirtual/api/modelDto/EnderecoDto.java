package com.lojavirtual.api.modelDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDto {
	
	private String cep; 
	private String logradouro;
	private String rua;
	private String numero;
	private String bairro;
	private String complemento;
	private String estado;
	private String cidade;

}
