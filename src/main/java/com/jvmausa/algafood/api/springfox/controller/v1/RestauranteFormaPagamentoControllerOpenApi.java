package com.jvmausa.algafood.api.springfox.controller.v1;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.jvmausa.algafood.api.exceptionhandler.Problem;
import com.jvmausa.algafood.api.springfox.model.exception.Problem404OpenApi;
import com.jvmausa.algafood.api.v1.model.FormaPagamentoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteFormaPagamentoControllerOpenApi {

	
	@ApiOperation("Lista as formas de pagamento aceitas pelo restaurante")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID de restaurante inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "ID de restaurante inexistente", response = Problem404OpenApi.class)
	})
	CollectionModel<FormaPagamentoModel> listar(@ApiParam(value = "ID de um restaurante", example = "1", required = true) 
										Long restauranteId);

	@ApiOperation("Desassocia uma forma de pagamento a um restaurante")
	@ApiResponses({
		@ApiResponse(code = 404, message = "ID inválido", response = Problem404OpenApi.class),
		@ApiResponse(code = 204, message = "Desassociação realizada com sucesso")
	})
	ResponseEntity<Void> desassociar(@ApiParam(value = "ID de um restaurante", example = "1", required = true)
								Long restauranteId, 
					@ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true)
								Long formaPagamentoId);

	@ApiOperation("Associa uma forma de pagamento a um restaurante")
	@ApiResponses({
		@ApiResponse(code = 404, message = "ID inválido", response = Problem404OpenApi.class),
		@ApiResponse(code = 204, message = "Associação realizada com sucesso")
	})
	ResponseEntity<Void> associar(@ApiParam(value = "ID de um restaurante", example = "1", required = true)
							Long restauranteId, 
							@ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true)
							Long formaPagamentoId);

}