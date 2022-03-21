package com.jvmausa.algafood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jvmausa.algafood.api.springfox.controller.v1.UsuarioGrupoControllerOpenApi;
import com.jvmausa.algafood.api.v1.AlgaLinks;
import com.jvmausa.algafood.api.v1.assembler.GrupoModelAssembler;
import com.jvmausa.algafood.api.v1.model.GrupoModel;
import com.jvmausa.algafood.core.security.AlgaSecurity;
import com.jvmausa.algafood.core.security.CheckSecurity;
import com.jvmausa.algafood.domain.model.Usuario;
import com.jvmausa.algafood.domain.service.CadastroUsuarioService;


@RestController
@RequestMapping(path = "/v1/usuarios/{usuarioId}/grupos", 
	produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioGrupoController implements UsuarioGrupoControllerOpenApi{

	@Autowired
	private CadastroUsuarioService cadastroUsuario;
	
	@Autowired
	private GrupoModelAssembler grupoModelAssembler;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity;    
	
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@Override
	@GetMapping
	public CollectionModel<GrupoModel> listar(@PathVariable Long usuarioId) {
		Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);
		
		CollectionModel<GrupoModel> gruposModel = grupoModelAssembler.
							toCollectionModel(usuario.getGrupos())
							.removeLinks();
		
		if (algaSecurity.podeEditarUsuariosGruposPermissoes()) {
			gruposModel.add(algaLinks.linkToUsuarioGrupoAssociar(usuarioId, null, "associar"));
			gruposModel.forEach(grupoModel -> {
				grupoModel.add(algaLinks.linkToUsuarioGrupoDesassociar(usuarioId, grupoModel.getId(), "desassociar"));
			});
		}
		return gruposModel;
	}
	
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@Override
	@DeleteMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		cadastroUsuario.desassociarGrupo(usuarioId, grupoId);
		return ResponseEntity.noContent().build();
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@Override
	@PutMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		cadastroUsuario.associarGrupo(usuarioId, grupoId);
		return ResponseEntity.noContent().build();
	}

	
}
