package com.lojavirtual.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.lojavirtual.domain.model.Pedido;

public interface PedidoRepository extends CustomJpaRepository<Pedido, Long>, JpaSpecificationExecutor<Pedido> {
	
	Optional<Pedido> findByCodigo(String codigo);

}
