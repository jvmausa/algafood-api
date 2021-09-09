package com.jvmausa.algafood.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@ApiModel("Problema")
@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {
	
	@ApiModelProperty(example = "400", position = 1)
	private Integer status;
	@ApiModelProperty(example = "2021-09-08T14:30:43.8861078Z", position = 15)
	private OffsetDateTime timestamp;
	
	@ApiModelProperty(example = "https://algafood.com.br/dados-invalidos", position = 6)
	private String type;	
	
	@ApiModelProperty(example = "Dados inválidos", position = 5)
	private String title;
	
	@ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.", position = 10)
	private String detail;
	
	@ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.", position = 20)
	private String userMessage;
	
	@ApiModelProperty(value = "Objetos ou campos que geraram os erros", position = 50)
	private List<Object> objects;
	
	@ApiModel(value = "Objeto Problema")
	@Getter
	@Builder
	public static class Object{
		
		@ApiModelProperty(example = "nome")
		private String name;
		
		@ApiModelProperty(example = "nome é obrigatório")
		private String userMessage;
		
	}
	
	
}
