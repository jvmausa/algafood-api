package com.jvmausa.algafood.api.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestauranteModel {

	/*
	 * classe do que é mostrado na representação no endpoint
	 */
	private Long id;
	private String nome;
	private BigDecimal taxaFrete;
	private Boolean ativo;
	private CozinhaModel cozinha;
	private EnderecoModel endereco;

}
