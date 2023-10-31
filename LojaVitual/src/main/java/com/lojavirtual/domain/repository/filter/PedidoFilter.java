package com.lojavirtual.domain.repository.filter;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.lojavirtual.domain.model.StatusPedido;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class PedidoFilter {
	
	private String nomeCliente;
	private Long formaPagamentoId;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime dataCriacaoInicio;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime dataCriacaoFim;
	
	private StatusPedido status;
}
