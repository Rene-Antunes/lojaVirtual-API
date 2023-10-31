package com.lojavirtual.api.modelDto.input;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoDtoInput {
	
	@NotNull
	private MultipartFile arquivo;
	
	@NotBlank
	private String descricao;

}
