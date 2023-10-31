package com.lojavirtual.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lojavirtual.domain.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	@Query("from Produto p where p.ativo = true")
	Page<Produto> findAtivosProdutos(Pageable pageable);
}
