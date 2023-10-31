package com.lojavirtual.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Embeddable
public class Endereco {
	
	@Column(name = "endereco_cep")
	private String cep;
	@Column(name = "endereco_logradouro")
	private String logradouro;
	@Column(name = "endereco_rua")
	private String rua;
	@Column(name = "endereco_numero")
	private String numero;
	@Column(name = "endereco_bairro")
	private String bairro;
	@Column(name = "endereco_complemento")
	private String complemento;
	@Column(name = "endereco_estado")
	private String estado;
	@Column(name = "endereco_cidade")
	private String cidade;
}

