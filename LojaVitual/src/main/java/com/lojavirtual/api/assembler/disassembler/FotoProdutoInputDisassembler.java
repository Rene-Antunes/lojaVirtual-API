package com.lojavirtual.api.assembler.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lojavirtual.api.modelDto.FotoProdutoDto;
import com.lojavirtual.api.modelDto.input.FotoProdutoDtoInput;
import com.lojavirtual.domain.model.FotoProduto;
@Component
public class FotoProdutoInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public FotoProduto toDomainObject(FotoProdutoDtoInput fotoProdutoDtoInput) {
		return modelMapper.map(fotoProdutoDtoInput, FotoProduto.class);
		
	}
	
	
	public void copyToDomainObject(FotoProdutoDtoInput fotoProdutoDtoInput,
			FotoProduto fotoProduto) {
		
			modelMapper.map(fotoProdutoDtoInput, fotoProduto);
	}
}
