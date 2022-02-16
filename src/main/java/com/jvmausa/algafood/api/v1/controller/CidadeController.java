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

import com.jvmausa.algafood.api.ResourceUriHelper;
import com.jvmausa.algafood.api.springfox.controller.CidadeControllerOpenApi;
import com.jvmausa.algafood.api.v1.assembler.CidadeInputDisassembler;
import com.jvmausa.algafood.api.v1.assembler.CidadeModelAssembler;
import com.jvmausa.algafood.api.v1.model.CidadeModel;
import com.jvmausa.algafood.api.v1.model.input.CidadeInput;
import com.jvmausa.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.jvmausa.algafood.domain.exception.NegocioException;
import com.jvmausa.algafood.domain.model.Cidade;
import com.jvmausa.algafood.domain.repository.CidadeRepository;
import com.jvmausa.algafood.domain.service.CadastroCidadeService;


@RestController
@RequestMapping(path = "/v1/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController implements CidadeControllerOpenApi {

	@Autowired
	CidadeRepository cidadeRepository;

	@Autowired
	private CadastroCidadeService cadastroCidade;

	@Autowired
	private CidadeModelAssembler cidadeModelAssembler;

	@Autowired
	private CidadeInputDisassembler cidadeInputDisassembler;

	@Override
	@GetMapping
	public CollectionModel<CidadeModel> listar() {
		
		return cidadeModelAssembler.toCollectionModel(cidadeRepository.findAll());
		
	}

	@Override
	@GetMapping("/{id}")
	public CidadeModel buscar(@PathVariable Long id) {
		Cidade cidade = cadastroCidade.buscarOuFalhar(id);
		CidadeModel cidadeModel = cidadeModelAssembler.toModel(cidade);
		
		return cidadeModel;
	}

	@Override
	@PostMapping
	public CidadeModel adicionar(@RequestBody @Valid CidadeInput cidadeInput) {
		Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
		
		 cidade = cadastroCidade.salvar(cidade);
		
		try {
			CidadeModel cidadeModel = cidadeModelAssembler.toModel(cidade);
			
			ResourceUriHelper.addUriInResponseHeader(cidadeModel.getId());
			
			return cidadeModel;
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e); // exception para http 400 bad request
		}
	}
	@Override
	@PutMapping("/{id}")
	public CidadeModel atualizar(@PathVariable Long id, @RequestBody @Valid CidadeInput cidadeInput) {
		
		Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(id);
		cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);
		
		try {
			return cidadeModelAssembler.toModel(cadastroCidade.salvar(cidadeAtual));
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e); // exception para http bad request

		}

	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		cadastroCidade.excluir(id);
	}

}
