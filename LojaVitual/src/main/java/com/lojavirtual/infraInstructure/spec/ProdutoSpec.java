package com.lojavirtual.infraInstructure.spec;

import java.util.ArrayList;

import org.springframework.data.jpa.domain.Specification;

import com.lojavirtual.domain.filter.ProdutoFilter;
import com.lojavirtual.domain.model.Produto;

import jakarta.persistence.criteria.Predicate;

public class ProdutoSpec {
	
	public static Specification<Produto> produtoFilter(ProdutoFilter filtro){
		
		return (root, query, builder) -> {
			
			var predicates = new ArrayList<Predicate>();
			
			if(filtro.getNomeProduto() != null) {
				predicates.add(builder.equal(root.get("nome"), filtro.getNomeProduto()));
			}
			
			if(filtro.getCategoria() != null) {
				predicates.add(builder.equal(root.get("categoria"), filtro.getCategoria()));
			}
			
			if(filtro.getAtivo() !=null) {
				predicates.add(builder.equal(root.get("ativo"), filtro.getAtivo()));
			}
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
		
		
	}

}
