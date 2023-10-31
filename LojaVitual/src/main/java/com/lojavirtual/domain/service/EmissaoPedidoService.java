package com.lojavirtual.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lojavirtual.domain.exception.NegocioException;
import com.lojavirtual.domain.exception.PedidoNaoEncontradoException;
import com.lojavirtual.domain.model.FormaPagamento;
import com.lojavirtual.domain.model.Pedido;
import com.lojavirtual.domain.model.Produto;
import com.lojavirtual.domain.repository.FormaPagamentoRepository;
import com.lojavirtual.domain.repository.PedidoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;

@Service
public class EmissaoPedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private CadastroFormaPagamentoService FormaPagamentoService;
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@Autowired
	private CadastroProdutoService produtoService;
		
	
	@Transactional
	public Pedido emitir(Pedido pedido) {
		
		validarPedido(pedido);
		validarItens(pedido);
		
		pedido.getValorFrete();
		pedido.calcValorTotal();
		
		return pedidoRepository.save(pedido);
	}

	
	
	private void validarPedido(Pedido pedido) {
		FormaPagamento formaPagamento = FormaPagamentoService.buscarOuFalhar(pedido.getFormaPagamento().getId());
		pedido.setFormaPagamento(formaPagamento);
		
		if (!aceitarFormaPagamento(formaPagamento)) {
			throw new NegocioException(String.format("Forma de pagamento '%s' não é aceita.", formaPagamento.getDescricao()));
		}
	}

	
	
	private void validarItens(Pedido pedido) {
		pedido.getItens().forEach(item -> {
			Produto produto = produtoService.buscarOuFalhar(item.getProduto().getId());
			
			item.setPedido(pedido);
			item.setProduto(produto);
			item.setPrecoUnitario(produto.getValor());
		});
	}
	
	
	public boolean aceitarFormaPagamento(FormaPagamento formaPagamento) {
	
		List<FormaPagamento> formaPagamentos = formaPagamentoRepository.findAll();
		
		return formaPagamentos.contains(formaPagamento);
	
	}

	public Pedido buscarOuFalhar(String codigoPedido) {
		return pedidoRepository.findByCodigo(codigoPedido)
				.orElseThrow(() -> new PedidoNaoEncontradoException(codigoPedido));
		
	}
	
}
