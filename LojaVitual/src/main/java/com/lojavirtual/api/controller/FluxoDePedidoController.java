package com.lojavirtual.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lojavirtual.domain.service.FluxoPedidoService;

@RestController
@RequestMapping(value = "/pedidos/{codigoPedido}")
public class FluxoDePedidoController {
	
	@Autowired
	private FluxoPedidoService pedidoService;
	
	
	@PutMapping("/confirmacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void confirmar(@PathVariable String codigoPedido) {
		pedidoService.confirmar(codigoPedido);
	}
	
	@PutMapping("/preparacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void preparar(@PathVariable String codigoPedido) {
		pedidoService.prepararPedido(codigoPedido);
	}
	
	@PutMapping("/enviado")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void enviar(@PathVariable String codigoPedido) {
		pedidoService.enviar(codigoPedido);
	}
	
	@PutMapping("/entregue")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void entregar(@PathVariable String codigoPedido) {
		pedidoService.entrgar(codigoPedido);
	}
	
	@PutMapping("/cancelado")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancelar(@PathVariable String codigoPedido) {
		pedidoService.cancelar(codigoPedido);
		
		
	}

}
