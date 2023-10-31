package com.lojavirtual.api.modelDto.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoIdDtoInput {
	@NotNull
	private Long id;
}
