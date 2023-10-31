package com.lojavirtual.api.modelDto.input;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDtoInput {
	
	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;
	@NotBlank
	private String categoria; 
	@NotNull
	@PositiveOrZero
	private BigDecimal valor;
	@NotNull
	private boolean ativo = true;
}
