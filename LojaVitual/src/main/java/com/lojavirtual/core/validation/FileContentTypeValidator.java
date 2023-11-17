package com.lojavirtual.core.validation;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileContentTypeValidator implements ConstraintValidator<FileContentType, MultipartFile> {
	
	private List<String> allowedContentTypes;

	public void initialize(FileContentType constraint) {
		this.allowedContentTypes = Arrays.asList(constraint.allowed());
	}
	
	@Override
	public boolean isValid(MultipartFile multpartFile, ConstraintValidatorContext context) {
		return multpartFile == null || this.allowedContentTypes.contains(multpartFile.getContentType());
	}
	
	
}
