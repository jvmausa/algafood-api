package com.jvmausa.algafood.api.springfox.model.v1;

import java.util.List;

import org.springframework.hateoas.Links;

import com.jvmausa.algafood.api.v1.model.PedidoResumoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@ApiModel("PedidosResumoModel")
@Getter
@Setter
public class PedidosResumoModelOpenApi {

	private PedidosResumoEmbeddedModelOpenApi _embedded;
	private Links _links;
	private PageModelOpenApi page;

	@ApiModel("PedidosResumoEmbeddedModel")
	@Data
	public class PedidosResumoEmbeddedModelOpenApi {

		private List<PedidoResumoModel> pedidos;

	}
}
