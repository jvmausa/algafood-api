package com.jvmausa.algafood.api.openapi.controller;

import java.util.List;

import com.jvmausa.algafood.api.exceptionhandler.Problem;
import com.jvmausa.algafood.api.model.EstadoModel;
import com.jvmausa.algafood.api.model.input.EstadoInput;
import com.jvmausa.algafood.api.openapi.model.exception.Problem404OpenApi;
import com.jvmausa.algafood.api.openapi.model.exception.Problem409OpenApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Estado")
public interface EstadoControllerOpenApi {

	
	@ApiOperation(value = "Lista os estados")
	List<EstadoModel> listar();

	
	@ApiOperation(value = "Busca estado por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do estado inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "ID do estado não encontrado", response = Problem404OpenApi.class)
	})
	EstadoModel buscar(@ApiParam(value = "ID de um Estado", example = "1", required = true) Long id);

	@ApiOperation(value = "Adiciona um novo estado")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Estado cadastrado")
	})
	EstadoModel adicionar(@ApiParam(name = "corpo", value = "Representação de um Estado", required = true) 
									EstadoInput estadoInput);

	
	@ApiOperation(value = "Adiciona um novo estado")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Estado atualizado")
	})
	EstadoModel atualizar(@ApiParam(name = "ID de um estado", required = true) Long id, 
						@ApiParam(name = "corpo", value = "Representação de um Estado", required = true) 
							EstadoInput estadoInput);

	@ApiOperation(value = "Remove um estado por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Estado excluído"),
		@ApiResponse(code = 404, message = "Estado não encontrado", response = Problem404OpenApi.class),
		@ApiResponse(code = 409, message = "Entidade em uso", response = Problem409OpenApi.class)
	})
	void remover(Long id);

}