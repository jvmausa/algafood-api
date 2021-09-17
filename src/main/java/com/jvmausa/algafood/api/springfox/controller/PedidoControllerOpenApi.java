package com.jvmausa.algafood.api.springfox.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jvmausa.algafood.api.exceptionhandler.Problem;
import com.jvmausa.algafood.api.model.PedidoModel;
import com.jvmausa.algafood.api.model.PedidoResumoModel;
import com.jvmausa.algafood.api.model.input.PedidoInput;
import com.jvmausa.algafood.domain.filter.PedidoFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {

	@ApiImplicitParams({
        @ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
                name = "campos", paramType = "query", type = "string")
    })
	@ApiOperation("Listar os pedidos")
	Page<PedidoResumoModel> pesquisar(PedidoFilter filtro, Pageable pageable);

	
	 @ApiImplicitParams({
	        @ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
	                name = "campos", paramType = "query", type = "string")
	    })
	@ApiOperation("Busca um pedido pelo ID")
	@ApiResponses({
		@ApiResponse(code = 404, message = "ID do pedido não encontrado", response = Problem.class)
	})
	PedidoModel buscar(@ApiParam(value = "Código de um pedido", example = "023caf9a-cb96-4010-bb3d-8409cb13f228", required = true) 
						String codigoPedido);

	@ApiOperation("Cadastrar um novo pedido")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Novo pedido cadastrado")
	})
	PedidoModel adicionar(@ApiParam(name = "corpo", value = "Representação de um novo pedido", required = true) PedidoInput pedidoInput);

}