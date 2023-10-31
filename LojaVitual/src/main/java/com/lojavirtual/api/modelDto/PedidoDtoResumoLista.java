package com.lojavirtual.api.modelDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.lojavirtual.domain.model.StatusPedido;

import lombok.Getter;
import lombok.Setter;

@JsonFilter("pedidoFilter")
@Getter
@Setter
public class PedidoDtoResumoLista {
	
	private String codigo;
	private BigDecimal subTotal;
	private BigDecimal valorFrete;
	private BigDecimal valorTotal;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataConfirmacao;
	private LocalDateTime dataPreparacao;
	private LocalDateTime dataEnviado;
	private LocalDateTime dataEntrega;
	private LocalDateTime dataCancelamento;
	private StatusPedido statusPedido;
	private FormaPagamentoDto formaPagamento;

	private ClienteDtoResumo cliente;
	
}
