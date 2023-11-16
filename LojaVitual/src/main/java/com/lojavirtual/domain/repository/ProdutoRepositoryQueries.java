package com.lojavirtual.domain.repository;

import com.lojavirtual.domain.model.FotoProduto;

public interface ProdutoRepositoryQueries {
	
	FotoProduto save(FotoProduto fotoProduto);
	void delete(FotoProduto foto);
}
