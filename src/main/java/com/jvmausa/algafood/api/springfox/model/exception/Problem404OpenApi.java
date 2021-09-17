package com.jvmausa.algafood.api.springfox.model.exception;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@ApiModel("Problema404")
@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem404OpenApi {

	@ApiModelProperty(example = "404", position = 1)
	private Integer status;
	@ApiModelProperty(example = "2021-09-08T14:30:43.8861078Z", position = 15)
	private OffsetDateTime timestamp;

	@ApiModelProperty(example = "https://algafood.com.br/recurso-nao-encontrado", position = 6)
	private String type;

	@ApiModelProperty(example = "Recurso não encontrado", position = 5)
	private String title;

	@ApiModelProperty(example = "Não existe cadastro de Cozinha com esse id 10", position = 10)
	private String detail;

	@ApiModelProperty(example = "Não existe cadastro de Cozinha com esse id 10", position = 20)
	private String userMessage;

}
