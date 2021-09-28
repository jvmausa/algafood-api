package com.jvmausa.algafood.api.springfox.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;

import com.jvmausa.algafood.api.exceptionhandler.Problem;
import com.jvmausa.algafood.api.model.GrupoModel;
import com.jvmausa.algafood.api.model.input.GrupoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {

	@ApiOperation("Lista grupos")
	CollectionModel<GrupoModel> listar();

	@ApiOperation("Busca grupo por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do grupo Inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
	})
	GrupoModel buscar(@ApiParam(value = "ID de um Grupo", example = "1", required = true) Long id);

	
	@ApiOperation("Adiciona um novo grupo")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Grupo cadastrado")
	})
	GrupoModel adicionar(@ApiParam(name = "corpo", value = "Representação de um novo grupo", required = true) GrupoInput grupoInput);

	@ApiOperation("Atualiza um grupo por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Grupo atualizado")
	})
	GrupoModel atualizar(@ApiParam(value = "ID de um Grupo", required = true) Long id, 
			@ApiParam(name = "corpo", value = "Representação de um grupo com novos dados", required = true) GrupoInput grupoInput);

	@ApiOperation("Deleta um Grupo por ID")
	void remover(@ApiParam(value = "D de um Grupo", required = true)Long id);

}