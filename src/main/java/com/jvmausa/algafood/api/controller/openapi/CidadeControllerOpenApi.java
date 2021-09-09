package com.jvmausa.algafood.api.controller.openapi;

import java.util.List;

import com.jvmausa.algafood.api.exceptionhandler.Problem;
import com.jvmausa.algafood.api.model.CidadeModel;
import com.jvmausa.algafood.api.model.input.CidadeInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cidades")
public interface CidadeControllerOpenApi {

	@ApiOperation("Lista cidades")
	List<CidadeModel> listar();

	@ApiOperation("Busca cidade por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da cidade Inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
	})
	CidadeModel buscar(@ApiParam(value = "ID de uma Cidade", example = "1") Long id);

	
	@ApiOperation("Adiciona uma nova cidade")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Cidade cadastrada")
	})
	CidadeModel adicionar(@ApiParam(name = "corpo", value = "Representação de uma nova cidade") CidadeInput cidadeInput);

	
	@ApiOperation("Atualiza uma cidade por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cidade atualizada")
	})
	CidadeModel atualizar(@ApiParam(value = "IDde uma Cidade") Long id, 
			@ApiParam(name = "corpo", value = "Representação de uma cidade com novos dados") CidadeInput cidadeInput);

	@ApiOperation("Deleta uma cidade por ID")
	void remover(@ApiParam(value = "ID de uma Cidade") Long id);
}