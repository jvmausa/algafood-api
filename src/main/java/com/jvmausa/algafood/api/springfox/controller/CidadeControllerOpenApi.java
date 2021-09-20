package com.jvmausa.algafood.api.springfox.controller;

import org.springframework.hateoas.CollectionModel;

import com.jvmausa.algafood.api.exceptionhandler.Problem;
import com.jvmausa.algafood.api.model.CidadeModel;
import com.jvmausa.algafood.api.model.input.CidadeInput;
import com.jvmausa.algafood.api.springfox.model.exception.Problem404OpenApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cidades")
public interface CidadeControllerOpenApi {

	@ApiOperation("Lista cidades")
	CollectionModel<CidadeModel> listar();

	@ApiOperation("Busca cidade por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da cidade Inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem404OpenApi.class)
	})
	CidadeModel buscar(@ApiParam(value = "ID de uma Cidade", example = "1", required = true) Long id);

	
	@ApiOperation("Adiciona uma nova cidade")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Cidade cadastrada")
	})
	CidadeModel adicionar(@ApiParam(name = "corpo", value = "Representação de uma nova cidade", required = true) 
								CidadeInput cidadeInput);

	
	@ApiOperation("Atualiza uma cidade por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cidade atualizada")
	})
	CidadeModel atualizar(@ApiParam(value = "IDde uma Cidade", required = true) Long id, 
			@ApiParam(name = "corpo", value = "Representação de uma cidade com novos dados", required = true) CidadeInput cidadeInput);

	@ApiOperation("Deleta uma cidade por ID")
	void remover(@ApiParam(value = "ID de uma Cidade", required = true) Long id);
}