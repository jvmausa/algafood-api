package com.jvmausa.algafood.api.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonView;
import com.jvmausa.algafood.api.model.view.RestauranteView;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "restaurantes")
@Setter
@Getter
public class RestauranteModel extends RepresentationModel<RestauranteModel>{

	/*
	 * classe do que é mostrado na representação no endpoint
	 */
	
	@ApiModelProperty(example = "5")
	@JsonView(RestauranteView.Resumo.class)
	private Long id;
	
	@ApiModelProperty(example = "Lanchonete do Tio Sam")
	@JsonView(RestauranteView.Resumo.class)
	private String nome;
	
	@ApiModelProperty(example = "11")
	@JsonView(RestauranteView.Resumo.class)
	private BigDecimal taxaFrete;
	
	@JsonView(RestauranteView.Resumo.class)
	private CozinhaModel cozinha;
	
	
	private Boolean ativo;
	private Boolean aberto;	
	private EnderecoModel endereco;
	
	
	

}
