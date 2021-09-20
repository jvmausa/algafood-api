package com.jvmausa.algafood.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cidades")
@Setter
@Getter
public class CidadeModel extends RepresentationModel<CidadeModel>{

	@ApiModelProperty("Id de uma Cidade")
	private Long id;
	@ApiModelProperty(example = "São Paulo")
	private String nome;
	
	private EstadoModel estado;
	
}
