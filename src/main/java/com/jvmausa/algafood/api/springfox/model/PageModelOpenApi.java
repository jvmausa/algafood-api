package com.jvmausa.algafood.api.springfox.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageModelOpenApi<T> {


	private List<T> content;
	
	@ApiModelProperty(example = "10", value = "Quantidade de registros por página")
	private Long size;
	
	@ApiModelProperty(example = "50", value = "Quantidade de registros")
	private Long totalElements;
	
	@ApiModelProperty(example = "5", value = "Quantidade de páginas")
	private Long totalPages;
	
	@ApiModelProperty(example = "0", value = "Página atual")
	private Long number;
	
}
