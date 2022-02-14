package com.jvmausa.algafood.api.v2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jvmausa.algafood.api.ResourceUriHelper;
import com.jvmausa.algafood.api.v2.assembler.CidadeInputDisassemblerV2;
import com.jvmausa.algafood.api.v2.assembler.CidadeModelAssemblerV2;
import com.jvmausa.algafood.api.v2.model.CidadeModelV2;
import com.jvmausa.algafood.api.v2.model.input.CidadeInputV2;
import com.jvmausa.algafood.core.web.AlgaMediaTypes;
import com.jvmausa.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.jvmausa.algafood.domain.exception.NegocioException;
import com.jvmausa.algafood.domain.model.Cidade;
import com.jvmausa.algafood.domain.repository.CidadeRepository;
import com.jvmausa.algafood.domain.service.CadastroCidadeService;

@RestController
@RequestMapping(path = "/cidades", produces = AlgaMediaTypes.V2_APPLICATIN_JSON_VALUE)
public class CidadeControllerV2 {

	@Autowired
	CidadeRepository cidadeRepository;

	@Autowired
	private CadastroCidadeService cadastroCidade;

	@Autowired
	private CidadeModelAssemblerV2 cidadeModelAssembler;

	@Autowired
	private CidadeInputDisassemblerV2 cidadeInputDisassembler;

	@GetMapping
	public CollectionModel<CidadeModelV2> listar() {

		return cidadeModelAssembler.toCollectionModel(cidadeRepository.findAll());

	}

	@GetMapping("/{id}")
	public CidadeModelV2 buscar(@PathVariable Long id) {
		Cidade cidade = cadastroCidade.buscarOuFalhar(id);
		CidadeModelV2 cidadeModel = cidadeModelAssembler.toModel(cidade);

		return cidadeModel;
	}

	@PostMapping
	public CidadeModelV2 adicionar(@RequestBody @Valid CidadeInputV2 cidadeInput) {
		Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);

		cidade = cadastroCidade.salvar(cidade);

		try {
			CidadeModelV2 cidadeModel = cidadeModelAssembler.toModel(cidade);

			ResourceUriHelper.addUriInResponseHeader(cidadeModel.getIdCidade());

			return cidadeModel;
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e); // exception para http 400 bad request
		}
	}

	@PutMapping("/{id}")
	public CidadeModelV2 atualizar(@PathVariable Long id, @RequestBody @Valid CidadeInputV2 cidadeInput) {

		Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(id);
		cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);

		try {
			return cidadeModelAssembler.toModel(cadastroCidade.salvar(cidadeAtual));
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e); // exception para http bad request

		}

	}

}
