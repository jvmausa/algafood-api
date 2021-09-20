package com.jvmausa.algafood.api.model;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EstadoModel extends RepresentationModel<EstadoModel> {

	private Long id;
	@ApiModelProperty(example = "São Paulo")
	private String nome;

}
