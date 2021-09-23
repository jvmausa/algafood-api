package com.jvmausa.algafood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.jvmausa.algafood.api.controller.CidadeController;
import com.jvmausa.algafood.api.controller.FormaPagamentoController;
import com.jvmausa.algafood.api.controller.PedidoController;
import com.jvmausa.algafood.api.controller.RestauranteController;
import com.jvmausa.algafood.api.controller.RestauranteProdutoController;
import com.jvmausa.algafood.api.controller.UsuarioController;
import com.jvmausa.algafood.api.model.PedidoModel;
import com.jvmausa.algafood.domain.model.Pedido;

@Component
public class PedidoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoModel> {

	public PedidoModelAssembler() {
		super(PedidoController.class, PedidoModel.class);

	}

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PedidoModel toModel(Pedido pedido) {
		PedidoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
		modelMapper.map(pedido, pedidoModel);

		TemplateVariables pageVariables = new TemplateVariables(
				new TemplateVariable("page", VariableType.REQUEST_PARAM),
				new TemplateVariable("size", VariableType.REQUEST_PARAM),
				new TemplateVariable("sort", VariableType.REQUEST_PARAM));

		TemplateVariables filtroVariables = new TemplateVariables(
				new TemplateVariable("restauranteId", VariableType.REQUEST_PARAM),
				new TemplateVariable("clienteId", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoInicio", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoFim", VariableType.REQUEST_PARAM)				
				);		
		String pedidosUrl = linkTo(PedidoController.class).toUri().toString();

		pedidoModel.add(Link.of(UriTemplate.of(pedidosUrl, pageVariables.concat(filtroVariables)), "pedidos"));

		pedidoModel.getRestaurante().add(
				linkTo(methodOn(RestauranteController.class).buscar(pedido.getRestaurante().getId())).withSelfRel());

		pedidoModel.getCliente()
				.add(linkTo(methodOn(UsuarioController.class).buscar(pedido.getCliente().getId())).withSelfRel());

		pedidoModel.getFormaPagamento()
				.add(linkTo(methodOn(FormaPagamentoController.class).buscar(pedido.getFormaPagamento().getId()))
						.withSelfRel());

		pedidoModel.getEnderecoEntrega().getCidade()
				.add(linkTo(methodOn(CidadeController.class).buscar(pedido.getEnderecoEntrega().getCidade().getId()))
						.withSelfRel());

		pedidoModel.getItens().forEach(item -> {
			item.add(linkTo(methodOn(RestauranteProdutoController.class).buscar(pedidoModel.getRestaurante().getId(),
					item.getProdutoId())).withRel("produto"));
		});

		return pedidoModel;
	}

}