package com.lojavirtual.domain.model;

import java.util.Arrays;
import java.util.List;

public enum StatusPedido {
	
	CRIADO("Criado"),
	CONFIRMADO("Confirmado", CRIADO),
	PEDIDO_SENDO_PREPARADO("Em preparo", CONFIRMADO),
	ENVIADO_PARA_TRANSPORTADORA("Enviado para transportadora", PEDIDO_SENDO_PREPARADO),
	ENTREGUE("Entregue", ENVIADO_PARA_TRANSPORTADORA),
	CANCELADO("Cancelado", CRIADO, CONFIRMADO, PEDIDO_SENDO_PREPARADO,ENVIADO_PARA_TRANSPORTADORA);
	
	private String descricao;
	private List<StatusPedido> statusAnteriores;
	
	private StatusPedido(String descricao, StatusPedido... statusAnteriores) {
		this.descricao = descricao;
		this.statusAnteriores = Arrays.asList(statusAnteriores);
		
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public boolean naoPodeAlterarPara(StatusPedido novoStatus) {
		return !novoStatus.statusAnteriores.contains(this);
	}

}
