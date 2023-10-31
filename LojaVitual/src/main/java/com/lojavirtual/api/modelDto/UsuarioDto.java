package com.lojavirtual.api.modelDto;

import java.util.Set;

import com.lojavirtual.domain.model.Grupo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDto {

	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private String celular;
	private Set<Grupo> grupos;
		
}
