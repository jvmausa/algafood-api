package com.jvmausa.algafood.api.springfox.controller;

import com.jvmausa.algafood.api.springfox.model.exception.Problem404OpenApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Pedidos")
public interface FluxoPedidoControllerOpenApi {

	
	@ApiOperation(value = "Confirma um pedido")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Pedido confirmado"),
		@ApiResponse(code = 404, message = "Código de pedido não encontrado", response = Problem404OpenApi.class)
	})
	void confirmar(@ApiParam(value = "Código de um pedido", example = "1", required = true) 
					String codigoPedido);

	
	@ApiOperation(value = "Confirma um pedido")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Cancela um pedido"),
		@ApiResponse(code = 404, message = "Código de pedido não encontrado", response = Problem404OpenApi.class)
	})
	void cancelar(@ApiParam(value = "Código de um pedido", example = "1", required = true) String codigoPedido);

	
	@ApiOperation(value = "Confirma um pedido")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Entrega de pedido registrado com sucesso"),
		@ApiResponse(code = 404, message = "Código de pedido não encontrado", response = Problem404OpenApi.class)
	})
	void entrega(@ApiParam(value = "Código de um pedido", example = "1", required = true) String codigoPedido);

}