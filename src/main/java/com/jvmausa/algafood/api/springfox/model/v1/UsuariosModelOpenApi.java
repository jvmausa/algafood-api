package com.jvmausa.algafood.api.springfox.model.v1;

import java.util.List;

import org.springframework.hateoas.Links;

import com.jvmausa.algafood.api.v1.model.UsuarioModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@ApiModel("UsuariosModel")
@Getter
@Setter
public class UsuariosModelOpenApi {

	private UsuariosEmbeddedModelOpenApi _embedded;
	private Links _links;

	@ApiModel("RestaurantesEmbeddedModel")
	@Data
	public class UsuariosEmbeddedModelOpenApi {

		private List<UsuarioModel> usuarios;

	}
	
}
