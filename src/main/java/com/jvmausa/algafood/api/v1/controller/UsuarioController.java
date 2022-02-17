package com.jvmausa.algafood.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jvmausa.algafood.api.springfox.controller.v1.UsuarioControllerOpenApi;
import com.jvmausa.algafood.api.v1.assembler.UsuarioInputDisassembler;
import com.jvmausa.algafood.api.v1.assembler.UsuarioModelAssembler;
import com.jvmausa.algafood.api.v1.model.UsuarioModel;
import com.jvmausa.algafood.api.v1.model.input.SenhaInput;
import com.jvmausa.algafood.api.v1.model.input.UsuarioComSenhaInput;
import com.jvmausa.algafood.api.v1.model.input.UsuarioInput;
import com.jvmausa.algafood.domain.model.Usuario;
import com.jvmausa.algafood.domain.repository.UsuarioRepository;
import com.jvmausa.algafood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping(path = "/v1/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController implements UsuarioControllerOpenApi {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CadastroUsuarioService cadastroUsuario;

	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;

	@Autowired
	private UsuarioInputDisassembler usuarioInputDisassembler;

	@Override
	@GetMapping
	public CollectionModel<UsuarioModel> listar() {
		return usuarioModelAssembler.toCollectionModel(usuarioRepository.findAll());
	}

	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioModel adicionar(@RequestBody @Valid UsuarioComSenhaInput usuarioInput) {
		Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioInput);
		usuario = cadastroUsuario.salvar(usuario);

		return usuarioModelAssembler.toModel(usuario);

	}
	
	@Override
	@GetMapping("/{id}")
    public UsuarioModel buscar(@PathVariable Long id) {
        Usuario usuario = cadastroUsuario.buscarOuFalhar(id);
        
        return usuarioModelAssembler.toModel(usuario);
    }
	

	@Override
	@PutMapping("/{id}")
	public UsuarioModel atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioInput usuarioInput) {
		Usuario usuarioAtual = cadastroUsuario.buscarOuFalhar(id);
		usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuarioAtual);
		usuarioAtual = cadastroUsuario.salvar(usuarioAtual);

		return usuarioModelAssembler.toModel(usuarioAtual);
	}

	@Override
	@PutMapping("/{id}/senha")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void alterarSenha(@PathVariable Long id, @RequestBody @Valid SenhaInput senha) {
		cadastroUsuario.alterarSenha(id, senha.getSenhaAtual(), senha.getNovaSenha());
	}

}
