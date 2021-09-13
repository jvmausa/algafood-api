package com.jvmausa.algafood.api.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import com.jvmausa.algafood.api.exceptionhandler.Problem;
import com.jvmausa.algafood.api.model.FormaPagamentoModel;
import com.jvmausa.algafood.api.model.input.FormaPagamentoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api(tags = "Formas de Pagamento")
public interface FormaPagamentoControllerOpenApi {

	@ApiOperation("Listar as formas de pagamento")
	ResponseEntity<List<FormaPagamentoModel>> listar(ServletWebRequest request);

	
	 @ApiOperation("Busca uma forma de pagamento por ID")
	    @ApiResponses({
	        @ApiResponse(code = 400, message = "ID da forma de pagamento inválido", response = Problem.class),
	        @ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
	    })
	ResponseEntity<FormaPagamentoModel> buscar(
			@ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true) Long id);

	 
	@ApiOperation("Cadastrar uma nova forma da pagamento")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Forma de pagamento cadastrada")
	})
	FormaPagamentoModel adicionar(@ApiParam(name = "corpo", value = "representação de uma nova forma de pagamento", required = true) 
									FormaPagamentoInput formaPagantoInput);

	
	@ApiOperation("Atualizar uma forma de pagamento por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Forma de pagamento atualizada"),
		@ApiResponse(code = 404, message = "Forma de pagamento não encontrada", response = Problem.class)
	})
	FormaPagamentoModel atualizar(@ApiParam(value = "ID de uma forma de pagamento", required = true) Long id, 
			@ApiParam(name = "corpo", value = "representação de uma nova forma de pagamento", required = true) FormaPagamentoInput formaPagantoInput);

	
	
	@ApiOperation("Remover uma forma de pagamento")
	void remover(@ApiParam(value = "ID de uma forma de pagamento", required = true) Long id);

}