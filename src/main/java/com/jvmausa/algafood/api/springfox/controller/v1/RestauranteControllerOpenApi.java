package com.jvmausa.algafood.api.springfox.controller.v1;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.jvmausa.algafood.api.exceptionhandler.Problem;
import com.jvmausa.algafood.api.v1.model.RestauranteModel;
import com.jvmausa.algafood.api.v1.model.RestauranteResumoModel;
import com.jvmausa.algafood.api.v1.model.input.RestauranteInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteControllerOpenApi {

	
	@ApiOperation(value = "Lista Restaurantes")
	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nome da projeção de pedidos", 
				name = "projecao", paramType = "query", type = "string", allowableValues = "completo")
	})
	CollectionModel<RestauranteModel> listar();

	
	@ApiOperation(value = "Lista Restaurante de forma resumida", hidden = true)
	CollectionModel<RestauranteResumoModel> listarResumido();
	@ApiOperation("Busca um restaurante por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do restaurante inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
	})
	public RestauranteModel buscar(
			@ApiParam(value = "ID de um restaurante", example = "1", required = true)
			Long restauranteId);
	
	
	@ApiOperation("Adiciona um novo Restaurante")
	@ApiResponse(code = 201, message = "Restaurant cadastrado")
	RestauranteModel adicionar(@ApiParam(name = "corpo", value = "Representação de um Restaurante", required = true)
									RestauranteInput restauranteInput);

	@ApiOperation("Atualizar um Restaurante")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Restaurante atualizado", response = Problem.class),
		@ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
	})
	RestauranteModel atualizar(@ApiParam(value = "ID de um Restaurante", example = "1") Long id, 
			@ApiParam(name = "corpo", value = "Representação de um Restaurante com os novos dados", required = true) 
					RestauranteInput restauranteInput);
	
	@ApiOperation("Ativar um Restaurante")
	@ApiResponses({
        @ApiResponse(code = 204, message = "Restaurante ativado com sucesso"),
        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
	ResponseEntity<Void> ativar(@ApiParam(value = "ID de um Restaurante inativo", example = "1", required = true) Long id);

	
	@ApiOperation("Inativar um Restaurante")
	@ApiResponses({
        @ApiResponse(code = 204, message = "Restaurante inativado com sucesso"),
        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
	ResponseEntity<Void> inativar(@ApiParam(value = "ID de um Restaurante ativo", required = true)Long id);

	
	@ApiResponses({
        @ApiResponse(code = 204, message = "Restaurante inativado com sucesso")
    })
	@ApiOperation("Ativar multiplos Restaurante")
	ResponseEntity<Void> ativarMultiplos(@ApiParam(value = "IDs dos Restaurante inativo", example = "1, 3, 5", required = true) List<Long> restauranteIds);

	
	@ApiOperation("Inativa múltiplos restaurantes")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Restaurantes inativados com sucesso")
    })
	ResponseEntity<Void> inativarMultiplos(@ApiParam(value = "IDs dos Restaurante ativos", example = "1, 3, 5", required = true) List<Long> restauranteIds);

	
	@ApiOperation("Abre um restaurante por ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Restaurante aberto com sucesso"),
        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
	ResponseEntity<Void> abrir( @ApiParam(value = "ID de um restaurante", example = "1", required = true) Long id);

	
	@ApiOperation("Fecha um restaurante por ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Restaurante fechado com sucesso"),
        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = Problem.class)
    })
	ResponseEntity<Void> fechar(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long id);

}