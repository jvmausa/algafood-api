package com.jvmausa.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.jvmausa.algafood.api.AlgaLinks;
import com.jvmausa.algafood.api.controller.RestauranteProdutoController;
import com.jvmausa.algafood.api.model.ProdutoModel;
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

	@Override
	public ProdutoModel toModel(Produto produto) {
		// usa as path variables para buscar recurso único, por isso tem mais de um
		// getId
		ProdutoModel produtoModel = createModelWithId(produto.getId(), produto, produto.getRestaurante().getId());

		modelMapper.map(produto, produtoModel);

		produtoModel.add(algaLinks.linkToProdutos(produto.getRestaurante().getId(), "restaurante-produtos"));

		return produtoModel;

	}
}