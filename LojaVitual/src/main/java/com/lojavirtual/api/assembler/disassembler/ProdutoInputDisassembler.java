package com.lojavirtual.api.assembler.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lojavirtual.api.modelDto.ProdutoDto;
import com.lojavirtual.api.modelDto.input.ProdutoDtoInput;
import com.lojavirtual.domain.model.Produto;
@Component
public class ProdutoInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Produto toDomainObject(ProdutoDtoInput produtoDtoInput) {
		return modelMapper.map(produtoDtoInput, Produto.class);
		
	}
	
	
	public void copyToDomainObject(ProdutoDtoInput produtoDtoInput,
			Produto produto) {
		
			modelMapper.map(produtoDtoInput, produto);
	}
	
}
