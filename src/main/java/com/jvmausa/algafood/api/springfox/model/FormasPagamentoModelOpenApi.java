package com.jvmausa.algafood.api.springfox.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.jvmausa.algafood.api.model.FormaPagamentoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("FormasPagamentoModel")
@Data
public class FormasPagamentoModelOpenApi {

	private FormasPagamentoEmbeddedModelOpenApi _embedded;
	private Links _links;
	
	@ApiModel("FormasPagamentoEmbeddedModel")
	@Data
	public class FormasPagamentoEmbeddedModelOpenApi {
		
		private List<FormaPagamentoModel> formasPagamento;
		
	}
	
	
}
