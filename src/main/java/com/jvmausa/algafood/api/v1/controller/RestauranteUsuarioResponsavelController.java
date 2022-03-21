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

import com.jvmausa.algafood.api.springfox.controller.v1.RestauranteUsuarioResponsavelControllerOpenApi;
import com.jvmausa.algafood.api.v1.AlgaLinks;
import com.jvmausa.algafood.api.v1.assembler.UsuarioModelAssembler;
import com.jvmausa.algafood.api.v1.model.UsuarioModel;
import com.jvmausa.algafood.core.security.AlgaSecurity;
import com.jvmausa.algafood.core.security.CheckSecurity;
import com.jvmausa.algafood.domain.model.Restaurante;
import com.jvmausa.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(path = "/v1/restaurantes/{restauranteId}/responsaveis", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteUsuarioResponsavelController implements RestauranteUsuarioResponsavelControllerOpenApi {

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;

	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;

	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private AlgaSecurity algaSecurity;   
	
	@CheckSecurity.Restaurantes.PodeConsultar
	@Override
	@GetMapping
	public CollectionModel<UsuarioModel> listar(@PathVariable Long restauranteId) {
		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

		CollectionModel<UsuarioModel> usuariosResponsaveis = usuarioModelAssembler
				.toCollectionModel(restaurante.getResponsaveis())
				.removeLinks();
		
		if (algaSecurity.podeGerenciarCadastroRestaurantes()) {
			usuariosResponsaveis.add(algaLinks.linkToResponsaveisRestaurante(restauranteId));
			usuariosResponsaveis.add(algaLinks.linkToResponsaveisRestauranteAssociar(restauranteId, "associar"));
			usuariosResponsaveis.getContent().forEach(usuarioResponsavel -> {
				usuarioResponsavel.add(algaLinks.linkToResponsaveisRestauranteDesassociar(restauranteId,
						usuarioResponsavel.getId(), "desassociar"));
			});
		}
		return usuariosResponsaveis;
		
	}

	@CheckSecurity.Restaurantes.PodeGerenciarCadastro
	@Override
	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		cadastroRestaurante.desassociarResponsavel(restauranteId, usuarioId);
		return ResponseEntity.noContent().build();
	}

	@CheckSecurity.Restaurantes.PodeGerenciarCadastro
	@Override
	@PutMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		cadastroRestaurante.associarResponsavel(restauranteId, usuarioId);
		return ResponseEntity.noContent().build();
	}

}
