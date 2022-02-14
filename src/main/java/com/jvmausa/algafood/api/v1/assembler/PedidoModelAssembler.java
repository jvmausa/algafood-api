package com.jvmausa.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.jvmausa.algafood.api.v1.AlgaLinks;
import com.jvmausa.algafood.api.v1.controller.PedidoController;
import com.jvmausa.algafood.api.v1.model.PedidoModel;
import com.jvmausa.algafood.domain.model.Pedido;

@Component
public class PedidoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoModel> {

	public PedidoModelAssembler() {
		super(PedidoController.class, PedidoModel.class);

	}

	@Autowired
	private AlgaLinks algaLinks;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PedidoModel toModel(Pedido pedido) {
		PedidoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
		modelMapper.map(pedido, pedidoModel);

		pedidoModel.getRestaurante().add(algaLinks.linkToRestaurante(pedido.getRestaurante().getId()));

		pedidoModel.getCliente().add(algaLinks.linkToUsuario(pedido.getCliente().getId()));

		pedidoModel.getFormaPagamento().add(algaLinks.linkToFormaPagamento(pedido.getFormaPagamento().getId()));

		pedidoModel.getEnderecoEntrega().getCidade()
				.add(algaLinks.linkToCidade(pedido.getEnderecoEntrega().getCidade().getId()));

		if (pedido.podeSerConfirmado()) {
			pedidoModel.add(algaLinks.linkToConfirmacaoPedido(pedido.getCodigo(), "confirmar"));

		} else if (pedido.podeSerCancelado()) {
			pedidoModel.add(algaLinks.linkToCancelamentoPedido(pedido.getCodigo(), "cancelar"));

		} else if (pedido.podeSerEntregue()) {
			pedidoModel.add(algaLinks.linkToEntregarPedido(pedido.getCodigo(), "entregar"));
		}

		pedidoModel.getItens().forEach(item -> {
			item.add(algaLinks.linkToProduto(pedidoModel.getRestaurante().getId(), item.getProdutoId(), "produto"));
		});

		return pedidoModel;
	}
}