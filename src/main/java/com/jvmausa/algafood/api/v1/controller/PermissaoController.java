package com.jvmausa.algafood.api.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jvmausa.algafood.api.springfox.controller.PermissaoControllerOpenApi;
import com.jvmausa.algafood.api.v1.assembler.PermissaoModel;
import com.jvmausa.algafood.api.v1.assembler.PermissaoModelAssembler;
import com.jvmausa.algafood.domain.model.Permissao;
import com.jvmausa.algafood.domain.repository.PermissaoRepository;

@RestController
@RequestMapping(path = "/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class PermissaoController implements PermissaoControllerOpenApi {

	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@Autowired
	private PermissaoModelAssembler permissaoModelAssembler;
	
	@Override
	@GetMapping
	public CollectionModel<PermissaoModel> listar() {
		List<Permissao> todasPermissoes = permissaoRepository.findAll();
		
		return permissaoModelAssembler.toCollectionModel(todasPermissoes);
	}
	
}
