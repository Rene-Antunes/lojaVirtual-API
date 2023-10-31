package com.lojavirtual.api.assembler.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lojavirtual.api.modelDto.PedidoDto;
import com.lojavirtual.api.modelDto.input.PedidoDtoInput;
import com.lojavirtual.domain.model.Pedido;

@Component
public class PedidoInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Pedido toDomainObject(PedidoDtoInput pedidoDtoInput) {
		return modelMapper.map(pedidoDtoInput, Pedido.class);
		
	}
	
	
	public void copyToDomainObject(PedidoDtoInput pedidoDtoInput,
			Pedido pedido) {
		
			modelMapper.map(pedidoDtoInput, pedido);
	}
}
