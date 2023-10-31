package com.lojavirtual.api.modelDto;

import java.util.Set;

import com.lojavirtual.domain.model.Permissao;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class GrupoDto {
	private Long id;
	private String nome;
	private Set<Permissao> permissoes;
	
}
