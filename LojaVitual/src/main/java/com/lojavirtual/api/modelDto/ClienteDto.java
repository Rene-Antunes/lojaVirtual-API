package com.lojavirtual.api.modelDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDto {

	private String nome;
	private String celular;
	private String telefone;
	private String email;
	
	private EnderecoDto endereco;
}
