package com.lojavirtual.api.modelDto.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SenhaDtoInput {
	
	@NotBlank
	private String senhaAtual;
	@NotBlank
	private String novaSenha;
	
}	
