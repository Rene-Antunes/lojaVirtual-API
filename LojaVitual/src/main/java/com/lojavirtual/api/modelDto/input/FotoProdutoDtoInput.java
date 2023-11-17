package com.lojavirtual.api.modelDto.input;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.lojavirtual.core.validation.FileContentType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoDtoInput {
	
	
	@NotNull
	@FileContentType(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
	private MultipartFile arquivo;
	
	@NotBlank
	private String descricao;

}
