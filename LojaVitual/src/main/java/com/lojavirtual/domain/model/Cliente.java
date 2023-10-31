package com.lojavirtual.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Data;

@Data
@Embeddable
public class Cliente {
	
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String celular;
	@Column(nullable = false)
	private String telefone;
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	@Embedded
	private Endereco endereco;
	
}
