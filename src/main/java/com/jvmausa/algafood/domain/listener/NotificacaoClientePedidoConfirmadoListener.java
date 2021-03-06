package com.jvmausa.algafood.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.jvmausa.algafood.domain.event.PedidoConfirmadoEvent;
import com.jvmausa.algafood.domain.model.Pedido;
import com.jvmausa.algafood.domain.service.EnvioEmailService;
import com.jvmausa.algafood.domain.service.EnvioEmailService.Mensagem;

@Component	
public class NotificacaoClientePedidoConfirmadoListener {

	@Autowired
	private EnvioEmailService envioEmail;

	@TransactionalEventListener
	public void aoConfirmarPedido(PedidoConfirmadoEvent event) {
		Pedido pedido = event.getPedido();
		
		var mensagem = Mensagem.builder()
				.assunto(pedido.getRestaurante().getNome() + " - Pedido Confirmado")
				.corpo("emails/pedido-confirmado.html")
				.variavel("pedido", pedido )
				.destinatario(pedido.getCliente().getEmail())
				.build();
		envioEmail.enviar(mensagem);
		
	}
	
	
}
