package com.lojavirtual.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.lojavirtual.domain.exception.NegocioException;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String codigo;
	
	private BigDecimal subTotal;
	private BigDecimal valorTotal;
	private BigDecimal valorFrete;
	
	@CreationTimestamp
	private LocalDateTime dataCriacao;
	private LocalDateTime dataConfirmacao;
	private LocalDateTime dataPreparacao;
	private LocalDateTime dataEnviado;
	private LocalDateTime dataEntrega;
	private LocalDateTime dataCancelamento;
	
    @Enumerated(EnumType.STRING)
	private StatusPedido statusPedido = StatusPedido.CRIADO;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private FormaPagamento formaPagamento;
	

	@Column(nullable = false)
	@Embedded
	private Cliente cliente;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> itens = new ArrayList<>();
	
	public void calcValorTotal() {
		
		getItens().forEach(ItemPedido::calcPrecoTotal);
		
		this.subTotal = getItens().stream()
				.map(item -> item.getPrecoTotal())
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		this.valorTotal = this.subTotal.add(this.valorFrete);
	}
	
		
	public void confirmar() {
		setStatus(StatusPedido.CONFIRMADO);
		setDataConfirmacao(LocalDateTime.now());
		
		//TODO implementar event
	}
	
	public void prepararPedido() {
		setStatus(StatusPedido.PEDIDO_SENDO_PREPARADO);
		setDataPreparacao(LocalDateTime.now());
		
	}
	
	public void enviar() {
		setStatus(StatusPedido.ENVIADO_PARA_TRANSPORTADORA);
		setDataEnviado(LocalDateTime.now());
	}
	
	public void entregar() {
		setStatus(StatusPedido.ENTREGUE);
		setDataEntrega(LocalDateTime.now());
	}
	
	public void cancelar() {
		setStatus(StatusPedido.CANCELADO);
		setDataCancelamento(LocalDateTime.now());
	}
	
	private void setStatus(StatusPedido novoStatus) {
		
		if(getStatusPedido().naoPodeAlterarPara(novoStatus)) {
			throw new NegocioException(
					String.format("Status do pedido %s não pode ser alterado de %s para %s",
							getCodigo(), getStatusPedido().getDescricao(), novoStatus.getDescricao()));
		}
		this.statusPedido = novoStatus;
	}
			
	@PrePersist
	private void gerarCodigo() {
		setCodigo(UUID.randomUUID().toString());
	}
}
