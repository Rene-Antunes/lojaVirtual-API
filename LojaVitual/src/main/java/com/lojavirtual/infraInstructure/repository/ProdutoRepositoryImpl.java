package com.lojavirtual.infraInstructure.repository;

import org.springframework.stereotype.Repository;

import com.lojavirtual.domain.model.FotoProduto;
import com.lojavirtual.domain.repository.ProdutoRepositoryQueries;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepositoryQueries{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	@Transactional
	public FotoProduto save(FotoProduto fotoProduto) {

		
		return manager.merge(fotoProduto);
	}

	@Override
	@Transactional
	public void delete(FotoProduto foto) {
		manager.remove(foto);
		
	}
	

}
