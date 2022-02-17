package com.jvmausa.algafood.api.springfox.controller.v1;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import com.jvmausa.algafood.api.exceptionhandler.Problem;
import com.jvmausa.algafood.api.springfox.model.exception.Problem404OpenApi;
import com.jvmausa.algafood.api.v1.model.FotoProdutoModel;
import com.jvmausa.algafood.api.v1.model.input.FotoProdutoInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Produtos")
public interface RestauranteProdutoFotoControllerOpenApi {

	
	@ApiOperation("Atualiza a foto do produto de um Restaurante")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Foto do produto atualizada"),
		@ApiResponse(code = 404, message = "ID não encontrado", response = Problem404OpenApi.class)
	})
	FotoProdutoModel atualizarFoto(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId,
						@ApiParam(value = "ID de um produto", example = "1", required = true) Long produtoId, 
						@ApiParam(value = "Representação de Foto de um produto", required = true) FotoProdutoInput fotoProdutoInput,
						@ApiParam(value = "Arquivo da foto do produto (máximo 500KB, apenas JPG e PNG)", required = true) MultipartFile arquivo)
			throws IOException;

	@ApiOperation(value = "Busca a foto de um Produto pelo ID do restaurante e do Produto", produces = "application/json, image/jpeg, image/png")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "ID não encontrado", response = Problem404OpenApi.class)
	})
	FotoProdutoModel buscar(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId, 
							@ApiParam(value = "ID de um produto", example = "1", required = true) Long produtoId);

	@ApiOperation(value = "Busca a foto do produto de um restaurante", hidden = true)
	ResponseEntity<?> servirFoto(Long restauranteId, Long produtoId, String acceptHeader)
			throws HttpMediaTypeNotAcceptableException;
	
	
//	void excluir(Long restauranteId, Long produtoId);

}