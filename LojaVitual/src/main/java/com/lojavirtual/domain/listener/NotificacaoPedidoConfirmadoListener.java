package com.lojavirtual.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.lojavirtual.domain.event.PedidoConfirmadoEvent;
import com.lojavirtual.domain.model.Pedido;
import com.lojavirtual.domain.service.EnvioEmailService;
import com.lojavirtual.domain.service.EnvioEmailService.Mensagem;

@Component
public class NotificacaoPedidoConfirmadoListener {
	
	@Autowired
	private EnvioEmailService envioEmailService;
	
	@TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
	public void aoConfirmarPedido(PedidoConfirmadoEvent event){
		Pedido pedido = event.getPedido();
		
		var mensagem = Mensagem.builder()
				.assunto("Loja Virtual - Pedido Confirmado")
				.corpo("pedido-confirmado.html")
				.variavel("pedido", pedido)
				.destinatario(pedido.getCliente().getEmail())
				.build();
		envioEmailService.enviar(mensagem);
	}
}
