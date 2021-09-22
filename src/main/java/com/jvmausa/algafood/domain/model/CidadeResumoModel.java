package com.jvmausa.algafood.domain.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "pedidos")
@Setter
@Getter
public class CidadeResumoModel extends RepresentationModel<CidadeResumoModel>{

	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Uberlância")
	private String nome;
	
	@ApiModelProperty(example = "Minas Gerais")
	private String nomeEstado;

}
