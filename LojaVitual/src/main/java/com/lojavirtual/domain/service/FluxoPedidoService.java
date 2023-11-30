package com.lojavirtual.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lojavirtual.domain.model.Pedido;
import com.lojavirtual.domain.repository.PedidoRepository;
import com.lojavirtual.domain.service.EnvioEmailService.Mensagem;

import jakarta.transaction.Transactional;

@Service
public class FluxoPedidoService {
	
	
	@Autowired
	private EmissaoPedidoService service;
	
	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private EnvioEmailService emEnvioEmailService;
	
	
	@Transactional
	public void  confirmar(String codigoPedido) {
		Pedido pedido = service.buscarOuFalhar(codigoPedido);
		pedido.confirmar();
		
		var mensagem = Mensagem.builder()
				.assunto("Loja Virtual - Pedido Confirmado")
				.corpo("pedido-confirmado.html")
				.variavel("pedido", pedido)
				.destinatario(pedido.getCliente().getEmail())
				.build();
		emEnvioEmailService.enviar(mensagem);
		
		repository.save(pedido);
	}
	
	@Transactional
	public void prepararPedido(String codigoPedido) {
		Pedido pedido = service.buscarOuFalhar(codigoPedido);
		pedido.prepararPedido();
		
		repository.save(pedido);
	}
	
	@Transactional
	public void enviar(String codigoPedido) {
		Pedido pedido = service.buscarOuFalhar(codigoPedido);
		pedido.enviar();
		
		repository.save(pedido);
	}
	
	@Transactional
	public void entrgar(String codigoPedido) {
		Pedido pedido = service.buscarOuFalhar(codigoPedido);
		pedido.entregar();
		
		repository.save(pedido);
	}
	
	@Transactional
	public void cancelar(String codigoPedido) {
		Pedido pedido = service.buscarOuFalhar(codigoPedido);
		pedido.cancelar();
		
		repository.save(pedido);
	}
}
