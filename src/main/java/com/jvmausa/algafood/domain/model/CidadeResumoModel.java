package com.jvmausa.algafood.domain.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeResumoModel {

	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Uberlância")
	private String nome;
	
	@ApiModelProperty(example = "Minas Gerais")
	private String nomeEstado;

}
