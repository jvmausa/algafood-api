package com.jvmausa.algafood.api.springfox.controller.v1;

import org.springframework.hateoas.CollectionModel;

import com.jvmausa.algafood.api.v1.assembler.PermissaoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Permissões")
public interface PermissaoControllerOpenApi {

	@ApiOperation("Lista as permissões")
	CollectionModel<PermissaoModel> listar();
	
}
