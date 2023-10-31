package com.lojavirtual.api.assembler.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lojavirtual.api.modelDto.ItemPedidoDto;
import com.lojavirtual.api.modelDto.input.ItemPedidoDtoInput;
import com.lojavirtual.domain.model.ItemPedido;
@Component
public class ItemPedidoInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ItemPedido toDomainObject(ItemPedidoDtoInput itemPedidoDtoInput) {
		return modelMapper.map(itemPedidoDtoInput, ItemPedido.class);
		
	}
	
	
	public void copyToDomainObject(ItemPedidoDtoInput itemPedidoDtoInput,
			ItemPedido itemPedido) {
		
			modelMapper.map(itemPedidoDtoInput, itemPedido);
	}
}
