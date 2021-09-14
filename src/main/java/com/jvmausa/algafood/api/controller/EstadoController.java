package com.jvmausa.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.jvmausa.algafood.api.assembler.EstadoInputDisassembler;
import com.jvmausa.algafood.api.assembler.EstadoModelAssembler;
import com.jvmausa.algafood.api.model.EstadoModel;
import com.jvmausa.algafood.api.model.input.EstadoInput;
import com.jvmausa.algafood.api.openapi.controller.EstadoControllerOpenApi;
import com.jvmausa.algafood.domain.model.Estado;
import com.jvmausa.algafood.domain.repository.EstadoRepository;
import com.jvmausa.algafood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController implements EstadoControllerOpenApi {

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CadastroEstadoService cadastroEstado;

	@Autowired
	private EstadoModelAssembler estadoModelAssembler;

	@Autowired
	private EstadoInputDisassembler estadoInputDisassembler;

	@Override
	@GetMapping()
	public List<EstadoModel> listar() {
		return estadoModelAssembler.toColletionModel(estadoRepository.findAll());
	}

	@Override
	@GetMapping("/{id}")
	public EstadoModel buscar(@PathVariable Long id) {
		Estado estado = cadastroEstado.buscarOuFalhar(id);
		return estadoModelAssembler.toModel(estado);

	}

	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoModel adicionar(@RequestBody @Valid EstadoInput estadoInput) {

		Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);
		return estadoModelAssembler.toModel(cadastroEstado.salvar(estado));

	}

	@Override
	@PutMapping("/{id}")
	public EstadoModel atualizar(@PathVariable Long id, @RequestBody @Valid EstadoInput estadoInput) {

		Estado estadoAtual = cadastroEstado.buscarOuFalhar(id);

		estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);

		return estadoModelAssembler.toModel(cadastroEstado.salvar(estadoAtual));
	}

	@Override
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		cadastroEstado.excluir(id);
	}

}
