package com.lojavirtual.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lojavirtual.domain.model.Pedido;
import com.lojavirtual.domain.model.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {


}
