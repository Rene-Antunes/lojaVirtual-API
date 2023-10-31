package com.lojavirtual.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lojavirtual.api.modelDto.ProdutoDto;
import com.lojavirtual.domain.model.Produto;

@Component
public class ProdutoDtoAssembler {
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	public ProdutoDto toDto(Produto produto) {
		
		return modelMapper.map(produto, ProdutoDto.class);
	}
	
	public List<ProdutoDto> toDtoCollection(Collection<Produto> produto){
		return  produto.stream()
				.map(prod -> toDto(prod))
				.collect(Collectors.toList());
	}
}
