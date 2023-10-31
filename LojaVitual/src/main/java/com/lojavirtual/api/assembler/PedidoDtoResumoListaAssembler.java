package com.lojavirtual.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lojavirtual.api.modelDto.PedidoDto;
import com.lojavirtual.api.modelDto.PedidoDtoResumo;
import com.lojavirtual.api.modelDto.PedidoDtoResumoLista;
import com.lojavirtual.domain.model.Pedido;

@Component
public class PedidoDtoResumoListaAssembler {
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	public PedidoDtoResumoLista toDto (Pedido pedido) {
		return modelMapper.map(pedido, PedidoDtoResumoLista.class);
	}
	
	
	public List<PedidoDtoResumoLista> toCollectionDto(Collection<Pedido> pedidos){
		
		return pedidos.stream()
				.map(pedido -> toDto(pedido))
				.collect(Collectors.toList());
	}
}
