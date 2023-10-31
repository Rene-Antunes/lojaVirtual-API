package com.lojavirtual.api.modelDto.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDtoInput {
	@NotBlank
	private String cep;
	@NotBlank
	private String logradouro;
	@NotBlank
	private String rua;
	@NotBlank
	private String numero;
	@NotBlank
	private String bairro;
	@NotBlank
	private String complemento;
	@NotBlank
	private String cidade;
	@NotBlank
	private String Estado;

}
