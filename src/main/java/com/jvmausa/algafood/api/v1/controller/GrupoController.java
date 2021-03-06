package com.jvmausa.algafood.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jvmausa.algafood.api.springfox.controller.v1.GrupoControllerOpenApi;
import com.jvmausa.algafood.api.v1.assembler.GrupoInputDisassembler;
import com.jvmausa.algafood.api.v1.assembler.GrupoModelAssembler;
import com.jvmausa.algafood.api.v1.model.GrupoModel;
import com.jvmausa.algafood.api.v1.model.input.GrupoInput;
import com.jvmausa.algafood.core.security.CheckSecurity;
import com.jvmausa.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.jvmausa.algafood.domain.exception.NegocioException;
import com.jvmausa.algafood.domain.model.Grupo;
import com.jvmausa.algafood.domain.repository.GrupoRepository;
import com.jvmausa.algafood.domain.service.CadastroGrupoService;


@RestController
@RequestMapping(path = "/v1/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoController implements GrupoControllerOpenApi {

	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private CadastroGrupoService cadastroGrupo;

	@Autowired
	private GrupoModelAssembler grupoModelAssembler;

	@Autowired
	private GrupoInputDisassembler grupoInputDisassembler;
	
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@Override
	@GetMapping
	public CollectionModel<GrupoModel> listar(){
		return grupoModelAssembler.toCollectionModel(grupoRepository.findAll());
		
	}
	
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@Override
	@GetMapping("/{id}")
	public GrupoModel buscar(@PathVariable Long id) {
		Grupo grupo = cadastroGrupo.buscarOuFalhar(id);
		return grupoModelAssembler.toModel(grupo);
		
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeAlterarUsuarioGrupoPermissoes
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GrupoModel adicionar(@RequestBody @Valid GrupoInput grupoInput) {
		
		Grupo grupo = grupoInputDisassembler.toDomainObject(grupoInput);
		
		try {
			return grupoModelAssembler.toModel(cadastroGrupo.salvar(grupo));
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
		
	}
	
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@Override
	@PutMapping("/{id}")
	public GrupoModel atualizar(@PathVariable Long id, @RequestBody @Valid GrupoInput grupoInput) {
		
		Grupo grupoAtual = cadastroGrupo.buscarOuFalhar(id);
		
		grupoInputDisassembler.copyToDomainObject(grupoInput, grupoAtual);
		
		try {
			return grupoModelAssembler
					.toModel(cadastroGrupo.salvar(grupoAtual));
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
		
	}
	
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@Override
	@DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        cadastroGrupo.excluir(id);	
    } 
	
}
