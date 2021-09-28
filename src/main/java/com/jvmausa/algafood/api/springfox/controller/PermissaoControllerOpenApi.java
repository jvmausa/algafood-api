package com.jvmausa.algafood.api.springfox.controller;

import org.springframework.hateoas.CollectionModel;

import com.jvmausa.algafood.api.assembler.PermissaoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Permissões")
public interface PermissaoControllerOpenApi {

	@ApiOperation("Lista as permissões")
	CollectionModel<PermissaoModel> listar();
	
}
