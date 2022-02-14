package com.jvmausa.algafood.api.v2.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeInputV2 {

	@ApiModelProperty(example = "São Paulo")
	@NotBlank
	private String nomeCidade;

	@NotNull
	private Long idEstado;

	
	
}
