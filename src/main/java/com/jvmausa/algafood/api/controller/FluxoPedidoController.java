package com.jvmausa.algafood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jvmausa.algafood.domain.service.FluxoPedidoService;

@RestController
@RequestMapping(value = "/pedidos/{id}")
public class FluxoPedidoController {

	
	@Autowired
	FluxoPedidoService fluxoPedido;
	
	@PutMapping("/confirmacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void confirmar(@PathVariable Long id) {
		fluxoPedido.confirmar(id);
		
	}
	
	@PutMapping("/cancelamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancelar(@PathVariable Long id) {
		fluxoPedido.cancelar(id);
		
	}
	
	@PutMapping("/entrega")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void entrega(@PathVariable Long id) {
		fluxoPedido.entregar(id);
		
	}
	
	
}  