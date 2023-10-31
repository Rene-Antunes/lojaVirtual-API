package com.lojavirtual.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lojavirtual.api.modelDto.FotoProdutoDto;
import com.lojavirtual.domain.model.FotoProduto;

@Component
public class FotoProdutoDtoAssembler {
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	public FotoProdutoDto toDto (FotoProduto fotoProduto) {
		return modelMapper.map(fotoProduto, FotoProdutoDto.class);
	}
}
