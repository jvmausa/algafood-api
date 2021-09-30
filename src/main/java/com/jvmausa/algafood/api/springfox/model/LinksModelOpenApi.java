package com.jvmausa.algafood.api.springfox.model;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@ApiModel("Links")
public class LinksModelOpenApi {

	private LinkModel rel;
	
	@Getter
	@Setter
	private class LinkModel{

		private String href;
		private boolean templated;
		
	}
	
	
}
