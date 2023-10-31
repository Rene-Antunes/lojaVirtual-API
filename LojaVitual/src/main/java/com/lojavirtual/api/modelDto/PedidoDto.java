package com.lojavirtual.api.modelDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.lojavirtual.domain.model.StatusPedido;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoDto {

	private Long id;
	private String codigo;
	private BigDecimal subTotal;
	private BigDecimal valorFrete;
	private BigDecimal valorTotal;
	private LocalDateTime dataCricao;
	private LocalDateTime dataConfirmacao;
	private LocalDateTime dataCancelamento;
	private StatusPedido statusPedido;
	
	private FormaPagamentoDto formaPagamento;
	

	private ClienteDto cliente;
	

	private List<ItemPedidoDto> itens = new ArrayList<>();
	
	
}
