package com.jvmausa.algafood.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jvmausa.algafood.api.springfox.controller.v1.EstadoControllerOpenApi;
import com.jvmausa.algafood.api.v1.assembler.EstadoInputDisassembler;
import com.jvmausa.algafood.api.v1.assembler.EstadoModelAssembler;
import com.jvmausa.algafood.api.v1.model.EstadoModel;
import com.jvmausa.algafood.api.v1.model.input.EstadoInput;
import com.jvmausa.algafood.core.security.CheckSecurity;
import com.jvmausa.algafood.domain.model.Estado;
import com.jvmausa.algafood.domain.repository.EstadoRepository;
import com.jvmausa.algafood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/v1/estados")
public class EstadoController implements EstadoControllerOpenApi {

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CadastroEstadoService cadastroEstado;

	@Autowired
	private EstadoModelAssembler estadoModelAssembler;

	@Autowired
	private EstadoInputDisassembler estadoInputDisassembler;

	
	@CheckSecurity.Estados.PodeConsultar
	@Override
	@GetMapping()
	public CollectionModel<EstadoModel> listar() {
		return estadoModelAssembler.toCollectionModel(estadoRepository.findAll());
	}

	@CheckSecurity.Estados.PodeConsultar
	@Override
	@GetMapping("/{id}")
	public EstadoModel buscar(@PathVariable Long id) {
		Estado estado = cadastroEstado.buscarOuFalhar(id);
		return estadoModelAssembler.toModel(estado);

	}

	@CheckSecurity.Estados.PodeEditar
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoModel adicionar(@RequestBody @Valid EstadoInput estadoInput) {

		Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);
		return estadoModelAssembler.toModel(cadastroEstado.salvar(estado));

	}

	@CheckSecurity.Estados.PodeEditar
	@Override
	@PutMapping("/{id}")
	public EstadoModel atualizar(@PathVariable Long id, @RequestBody @Valid EstadoInput estadoInput) {

		Estado estadoAtual = cadastroEstado.buscarOuFalhar(id);

		estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);

		return estadoModelAssembler.toModel(cadastroEstado.salvar(estadoAtual));
	}

	
	@CheckSecurity.Estados.PodeEditar
	@Override
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		cadastroEstado.excluir(id);
	}

}
