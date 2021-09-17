package com.jvmausa.algafood.api.springfox.model.exception;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@ApiModel("Problema409")
@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem409OpenApi {

	@ApiModelProperty(example = "409", position = 1)
	private Integer status;
	@ApiModelProperty(example = "2021-09-08T14:30:43.8861078Z", position = 15)
	private OffsetDateTime timestamp;

	@ApiModelProperty(example = "https://algafood.com.br/entidade-em-uso", position = 6)
	private String type;

	@ApiModelProperty(example = "Entidade em uso", position = 5)
	private String title;

	@ApiModelProperty(example = "Cozinha de código 1 não pode ser removida pois está em uso", position = 10)
	private String detail;

	@ApiModelProperty(example = "Cozinha de código 1 não pode ser removida pois está em uso0", position = 20)
	private String userMessage;

}
