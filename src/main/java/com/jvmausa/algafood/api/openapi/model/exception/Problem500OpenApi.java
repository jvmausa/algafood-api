package com.jvmausa.algafood.api.openapi.model.exception;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@ApiModel("Problema500")
@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem500OpenApi {

	@ApiModelProperty(example = "500", position = 1)
	private Integer status;
	@ApiModelProperty(example = "2021-09-08T14:30:43.8861078Z", position = 15)
	private OffsetDateTime timestamp;

	@ApiModelProperty(example = "https://algafood.com.br/erro-de-sistema", position = 6)
	private String type;

	@ApiModelProperty(example = "Erro de Sistema", position = 5)
	private String title;

	@ApiModelProperty(example = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o problema persistir, entre em contato com o administrador do sistema.", position = 10)
	private String detail;

	@ApiModelProperty(example = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o problema persistir, entre em contato com o administrador do sistema.", position = 20)
	private String userMessage;

}
