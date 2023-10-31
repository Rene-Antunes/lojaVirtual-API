package com.lojavirtual.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lojavirtual.api.modelDto.PedidoDto;
import com.lojavirtual.api.modelDto.PedidoDtoResumo;
import com.lojavirtual.domain.model.Pedido;

@Component
public class PedidoDtoResumoAssembler {
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	public PedidoDtoResumo toDto (Pedido pedido) {
		return modelMapper.map(pedido, PedidoDtoResumo.class);
	}
	
	
	public List<PedidoDtoResumo> toCollectionDto(Collection<Pedido> pedidos){
		
		return pedidos.stream()
				.map(pedido -> toDto(pedido))
				.collect(Collectors.toList());
	}
}
