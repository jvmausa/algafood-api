package com.jvmausa.algafood.api.springfox.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.jvmausa.algafood.api.model.ProdutoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@ApiModel("ProdutosModel")
@Getter
@Setter
public class ProdutosModelOpenApi {

	private ProdutosEmbeddedModelOpenApi _embedded;
	private Links _links;

	@ApiModel("ProdutosEmbeddedModel")
	@Data
	public class ProdutosEmbeddedModelOpenApi {

		private List<ProdutoModel> produtos;

	}
	
}
