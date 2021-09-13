package com.jvmausa.algafood.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestauranteInput {
	
	/*
	 * classe do que é necessário pra ser inserido ao adicionar novo dado no endpoint
	 * */
	
	@ApiModelProperty(example = "Cozinha Mineira")
	@NotBlank
	private String nome;
	
	@ApiModelProperty(example = "10")
	@NotNull
	@PositiveOrZero
	private BigDecimal taxaFrete;
	
	
	@Valid
	@NotNull
	private CozinhaIdInput cozinha;
	
	@Valid
	@NotNull
	private EnderecoInput endereco;
	

}
