package com.jvmausa.algafood.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jvmausa.algafood.domain.exception.NegocioException;
import com.jvmausa.algafood.domain.model.Pedido;
import com.jvmausa.algafood.domain.model.StatusPedido;

@Service
public class FluxoPedidoService {

	@Autowired
	EmissaoPedidoService emissaoPedido;

	@Transactional
	public void confirmar(Long id) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(id);

		if (!pedido.getStatus().equals(StatusPedido.CRIADO)) {
			throw new NegocioException(String.format("Status do pedido %d não pode ser alterado de %s para %s",
					pedido.getId(), pedido.getStatus(), StatusPedido.CONFIRMADO.getDescricao()));
		}

		pedido.setStatus(StatusPedido.CONFIRMADO);
		pedido.setDataConfirmacao(OffsetDateTime.now());

	}

	@Transactional
	public void cancelar(Long id) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(id);

		if (!pedido.getStatus().equals(StatusPedido.CRIADO)) {
			throw new NegocioException(String.format("Status do pedido %d não pode ser alterado de %s para %s",
					pedido.getId(), pedido.getStatus(), StatusPedido.CANCELADO.getDescricao()));
		}

		pedido.setStatus(StatusPedido.CANCELADO);
		pedido.setDataCancelamento(OffsetDateTime.now());

	}
	
	@Transactional
	public void entregar(Long id) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(id);

		if (!pedido.getStatus().equals(StatusPedido.CONFIRMADO)) {
			throw new NegocioException(String.format("Status do pedido %d não pode ser alterado de %s para %s",
					pedido.getId(), pedido.getStatus(), StatusPedido.ENTREGUE.getDescricao()));
		}

		pedido.setStatus(StatusPedido.ENTREGUE);
		pedido.setDataEntrega(OffsetDateTime.now());

	}

}
