package com.lojavirtual.api.modelDto.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDtoInput {
	
	@NotBlank
	private String nome;
	@NotBlank
	private String celular;
	@NotBlank
	private String telefone;
	@NotBlank
	@Email
	private String email;
	@NotNull
	@Valid
	private EnderecoDtoInput endereco;
}
