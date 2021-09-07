package com.jvmausa.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//@ApiModel(value = "Cidade", description = "Representa uma cidade")
public class CidadeModel {

	@ApiModelProperty("Id de uma Cidade")
	private Long id;
	@ApiModelProperty(example = "São Paulo")
	private String nome;
	private EstadoModel estado;
	
}
