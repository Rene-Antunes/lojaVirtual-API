package com.lojavirtual.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lojavirtual.api.modelDto.ItemPedidoDto;
import com.lojavirtual.domain.model.ItemPedido;

@Component
public class ItemPedidoDtoAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ItemPedidoDto toDto(ItemPedido itemPedido) {
		return modelMapper.map(itemPedido, ItemPedidoDto.class);
	}
	
	public List<ItemPedidoDto> toCollectionDto(Collection<ItemPedido> itensPedido){
		
		return itensPedido.stream()
				.map(item -> toDto(item))
				.collect(Collectors.toList());
	}
}
