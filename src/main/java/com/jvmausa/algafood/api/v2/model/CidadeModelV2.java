package com.jvmausa.algafood.api.v2.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cidades")
@Setter
@Getter
public class CidadeModelV2 extends RepresentationModel<CidadeModelV2> {

	@ApiModelProperty("Id de uma Cidade")
	private Long idCidade;
	@ApiModelProperty(example = "São Paulo")
	private String nomeCidade;

	private Long idEstado;
	private String nomeEstado;

}
