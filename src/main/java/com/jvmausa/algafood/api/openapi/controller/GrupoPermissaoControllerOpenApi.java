package com.jvmausa.algafood.api.openapi.controller;

import java.util.List;

import com.jvmausa.algafood.api.assembler.PermissaoModel;
import com.jvmausa.algafood.api.exceptionhandler.Problem;
import com.jvmausa.algafood.api.openapi.model.exception.Problem404OpenApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api(tags = "Grupos")
public interface GrupoPermissaoControllerOpenApi {

	
	@ApiOperation("Lista o nome e as permissões de cada Grupo pelo ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do grupo inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "ID de grupo não encontrado", response = Problem404OpenApi.class)
	})
	List<PermissaoModel> listar(@ApiParam(value = "ID de um grupo", required = true) Long grupoId);

	@ApiOperation("Desassoria um Grupo com uma permissão")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Desassociação feita com sucesso"),
		@ApiResponse(code = 404, message = "ID de grupo não encontrado", response = Problem.class)
	})
	void desassociar(@ApiParam(value = "ID de um grupo", required = true) Long grupoId, 
					@ApiParam(value = "ID de uma permissao", required = true) Long permissaoId);

	
	@ApiOperation("Desassoria um Grupo com uma permissão")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Associação feita com sucesso"),
		@ApiResponse(code = 404, message = "ID de grupo não encontrado", response = Problem.class)
	})
	void associar(@ApiParam(value = "ID de um grupo", required = true) Long grupoId, 
			@ApiParam(value = "ID de uma permissao", required = true) Long permissaoId);

}