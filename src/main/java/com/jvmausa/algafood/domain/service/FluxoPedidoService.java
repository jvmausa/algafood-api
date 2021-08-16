package com.jvmausa.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jvmausa.algafood.domain.model.Pedido;

@Service
public class FluxoPedidoService {

	@Autowired
	EmissaoPedidoService emissaoPedido;

	@Transactional
	public void confirmar(Long id) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(id);	
		pedido.confirmar();

	}

	@Transactional
	public void cancelar(Long id) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(id);
		pedido.cancelar();

	}
	
	@Transactional
	public void entregar(Long id) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(id);
		pedido.entregar();

	}

}
