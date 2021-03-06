package com.jvmausa.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.jvmausa.algafood.api.v1.AlgaLinks;
import com.jvmausa.algafood.api.v1.controller.RestauranteProdutoController;
import com.jvmausa.algafood.api.v1.model.ProdutoModel;
import com.jvmausa.algafood.core.security.AlgaSecurity;
import com.jvmausa.algafood.domain.model.Produto;

@Component
public class ProdutoModelAssembler extends RepresentationModelAssemblerSupport<Produto, ProdutoModel> {

	public ProdutoModelAssembler() {
		super(RestauranteProdutoController.class, ProdutoModel.class);
	}

	@Autowired
	private AlgaLinks algaLinks;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaSecurity algaSecurity;

	@Override
	public ProdutoModel toModel(Produto produto) {
		// usa as path variables para buscar recurso único, por isso tem mais de um
		// getId
		ProdutoModel produtoModel = createModelWithId(produto.getId(), produto, produto.getRestaurante().getId());

		modelMapper.map(produto, produtoModel);

		if (algaSecurity.podeConsultarRestaurantes()) {
			produtoModel.add(algaLinks.linkToProdutos(produto.getRestaurante().getId(), "restaurante-produtos"));
			produtoModel.add(algaLinks.linkToProdutoFoto(produto.getRestaurante().getId(), produto.getId(), "foto"));
		}

		return produtoModel;

	}
}