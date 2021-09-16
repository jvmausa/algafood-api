package com.jvmausa.algafood.api.assembler;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PermissaoModel {

	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "GERENCIAR ESTOQUE")
	private String nome;
	
	@ApiModelProperty(example = "Permite Gerencial estoque do restaurante")
	private String descricao;

	
}
