package com.lojavirtual.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lojavirtual.domain.model.FotoProduto;
import com.lojavirtual.domain.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>, ProdutoRepositoryQueries, JpaSpecificationExecutor<Produto> {

	@Query("from Produto p where p.ativo = true")
	Page<Produto> findAtivosProdutos(Pageable pageable);
	
	@Query("from FotoProduto f where f.produto.id = :produtoId")
	Optional<FotoProduto> findFotoById(Long produtoId);
}
