package com.lojavirtual.api.modelDto.input;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoDtoInput {

	@NotNull
	@Valid
	private FormaPagamentoIdDtoInput formaPagamento;
	
	@NotNull
	private BigDecimal valorFrete;
	
	@NotNull
	@Valid
	private ClienteDtoInput cliente;
	
	@NotNull
	@Valid
	@Size(min = 1)
	private List<ItemPedidoDtoInput> itens = new ArrayList<>();
	
	
}
