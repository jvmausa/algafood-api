package com.jvmausa.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jvmausa.algafood.api.assembler.PermissaoModel;
import com.jvmausa.algafood.api.assembler.PermissaoModelAssembler;
import com.jvmausa.algafood.api.springfox.controller.GrupoPermissaoControllerOpenApi;
import com.jvmausa.algafood.domain.model.Grupo;
import com.jvmausa.algafood.domain.service.CadastroGrupoService;

@RestController
@RequestMapping(value = "/grupos/{grupoId}/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoPermissaoController implements GrupoPermissaoControllerOpenApi {

	@Autowired
	private CadastroGrupoService cadastroGrupo;
	
	@Autowired
	private PermissaoModelAssembler permissaoModelAssembler;
	
	@Override
	@GetMapping
	public List<PermissaoModel> listar(@PathVariable Long grupoId) {
		Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);
		
		return permissaoModelAssembler.toCollectionModel(grupo.getPermissoes());
	}
	
	@Override
	@DeleteMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		cadastroGrupo.desassociarPermissao(grupoId, permissaoId);
	}
	
	@Override
	@PutMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		cadastroGrupo.associarPermissao(grupoId, permissaoId);
	}

}