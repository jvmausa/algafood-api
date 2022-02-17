package com.jvmausa.algafood.api.springfox.model.v1;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("PageModel")
@Getter
@Setter
public class PageModelOpenApi {
	
	@ApiModelProperty(example = "10", value = "Quantidade de registros por página")
	private Long size;
	
	@ApiModelProperty(example = "50", value = "Quantidade de registros")
	private Long totalElements;
	
	@ApiModelProperty(example = "5", value = "Quantidade de páginas")
	private Long totalPages;
	
	@ApiModelProperty(example = "0", value = "Página atual")
	private Long number;
	
}
