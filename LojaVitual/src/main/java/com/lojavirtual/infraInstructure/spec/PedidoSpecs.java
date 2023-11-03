package com.lojavirtual.infraInstructure.spec;

import java.util.ArrayList;

import org.springframework.data.jpa.domain.Specification;

import com.lojavirtual.domain.filter.PedidoFilter;
import com.lojavirtual.domain.model.Pedido;

import jakarta.persistence.criteria.Predicate;

public class PedidoSpecs {
	
	public static Specification<Pedido> usandoFiltro(PedidoFilter filtro){
		return (root, query, builder) -> {
			
			if(Pedido.class.equals(query.getResultType())) {
					root.fetch("formaPagamento");
					root.fetch("itens");
			}
			
			var predicates = new ArrayList<Predicate>();
			
			if(filtro.getNomeCliente() != null) {
				predicates.add(builder.equal(root.get("cliente").get("nome"), filtro.getNomeCliente()));
			}
			
			if(filtro.getFormaPagamentoId() != null) {
				predicates.add(builder.equal(root.get("formaPagamento").get("id"), filtro.getFormaPagamentoId()));
			}
			
			if(filtro.getDataCriacaoInicio() != null) {
				predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
			}
			
			if(filtro.getDataCriacaoFim() != null) {
				predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));
			}
			
			if(filtro.getStatus() != null) {
				predicates.add(builder.equal(root.get("statusPedido"), filtro.getStatus()));
			}
			
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
