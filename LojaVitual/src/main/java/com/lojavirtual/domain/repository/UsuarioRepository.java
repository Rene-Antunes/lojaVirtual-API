package com.lojavirtual.domain.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lojavirtual.domain.model.Usuario;

@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByEmail(String email);
}
