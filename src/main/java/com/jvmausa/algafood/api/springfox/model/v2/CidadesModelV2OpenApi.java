package com.jvmausa.algafood.api.springfox.model.v2;

import java.util.List;

import org.springframework.hateoas.Links;

import com.jvmausa.algafood.api.v2.model.CidadeModelV2;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("CidadesModel")
@Data
public class CidadesModelV2OpenApi {

	private CidadeEmbeddedModelOpenApi _embedded;
	private Links _links;
	
	@ApiModel("CidadesEmbeddedModel")
	@Data
	public class CidadeEmbeddedModelOpenApi {
		
		private List<CidadeModelV2> cidades;
		
	}
	
}
