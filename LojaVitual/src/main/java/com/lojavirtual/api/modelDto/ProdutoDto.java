package com.lojavirtual.api.modelDto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDto {
	
	private Long id;
	private String nome;
	private String descricao;
	private String categoria; 
	private BigDecimal valor;
	private Boolean ativo;
}
