package com.jvmausa.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.jvmausa.algafood.api.v1.AlgaLinks;
import com.jvmausa.algafood.api.v1.controller.PedidoController;
import com.jvmausa.algafood.api.v1.model.PedidoResumoModel;
import com.jvmausa.algafood.core.security.AlgaSecurity;
import com.jvmausa.algafood.domain.model.Pedido;

@Component
public class PedidoResumoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoModel> {

	public PedidoResumoModelAssembler() {
		super(PedidoController.class, PedidoResumoModel.class);
	}

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaLinks algaLinks;

	@Autowired
	private AlgaSecurity algaSecurity;  
	
	@Override
	public PedidoResumoModel toModel(Pedido pedido) {

		PedidoResumoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
		modelMapper.map(pedido, pedidoModel);

		 if (algaSecurity.podePesquisarPedidos()) {
		        pedidoModel.add(algaLinks.linkToPedidos("pedidos"));
		    }
		    
		    if (algaSecurity.podeConsultarRestaurantes()) {
		        pedidoModel.getRestaurante().add(
		                algaLinks.linkToRestaurante(pedido.getRestaurante().getId()));
		    }

		    if (algaSecurity.podeConsultarUsuariosGruposPermissoes()) {
		        pedidoModel.getCliente().add(algaLinks.linkToUsuario(pedido.getCliente().getId()));
		    }

		return pedidoModel;
	}

}