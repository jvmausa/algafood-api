package com.jvmausa.algafood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "estados")
@Setter
@Getter
public class EstadoModel extends RepresentationModel<EstadoModel> {

	private Long id;
	@ApiModelProperty(example = "São Paulo")
	private String nome;

}
