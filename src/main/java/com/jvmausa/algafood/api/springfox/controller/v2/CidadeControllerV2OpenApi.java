package com.jvmausa.algafood.api.springfox.controller.v2;

import org.springframework.hateoas.CollectionModel;

import com.jvmausa.algafood.api.exceptionhandler.Problem;
import com.jvmausa.algafood.api.springfox.model.exception.Problem404OpenApi;
import com.jvmausa.algafood.api.v2.model.CidadeModelV2;
import com.jvmausa.algafood.api.v2.model.input.CidadeInputV2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cidades")
public interface CidadeControllerV2OpenApi {

	@ApiOperation("Lista cidades")
	CollectionModel<CidadeModelV2> listar();

	@ApiOperation("Busca cidade por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da cidade Inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem404OpenApi.class)
	})
	CidadeModelV2 buscar(@ApiParam(value = "ID de uma Cidade", example = "1", required = true) Long id);

	
	@ApiOperation("Adiciona uma nova cidade")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Cidade cadastrada")
	})
	CidadeModelV2 adicionar(@ApiParam(name = "corpo", value = "Representação de uma nova cidade", required = true) 
								CidadeInputV2 cidadeInput);

	
	@ApiOperation("Atualiza uma cidade por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cidade atualizada")
	})
	CidadeModelV2 atualizar(@ApiParam(value = "IDde uma Cidade", required = true) Long id, 
			@ApiParam(name = "corpo", value = "Representação de uma cidade com novos dados", required = true) CidadeInputV2 cidadeInput);

}