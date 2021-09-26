package com.jvmausa.algafood.api.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "restaurante")
@Setter
@Getter
public class RestauranteModel extends RepresentationModel<RestauranteModel>{

	/*
	 * classe do que é mostrado na representação no endpoint
	 */

	@ApiModelProperty(example = "5")
	private Long id;

	@ApiModelProperty(example = "Lanchonete do Tio Sam")
	private String nome;

	@ApiModelProperty(example = "11")
	private BigDecimal taxaFrete;

	private CozinhaModel cozinha;

	private Boolean ativo;
	private Boolean aberto;
	private EnderecoModel endereco;

}
