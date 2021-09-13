package com.jvmausa.algafood.api.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonView;
import com.jvmausa.algafood.api.model.view.RestauranteView;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestauranteModel {

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
