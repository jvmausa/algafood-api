package com.jvmausa.algafood.api.springfox.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.jvmausa.algafood.api.model.GrupoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("GruposModel")
@Data
public class GruposModelOpenApi {

	private GruposEmbeddedModelOpenApi _embedded;
	private Links _links;
	
	@ApiModel("GruposModelEmbeddedModel")
	@Data
	public class GruposEmbeddedModelOpenApi {
		
		private List<GrupoModel> grupos;
		
	}
	
	
	
}
