package com.jvmausa.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
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

import com.jvmausa.algafood.api.assembler.CidadeModelAssembler;
import com.jvmausa.algafood.api.model.CidadeModel;
import com.jvmausa.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.jvmausa.algafood.domain.exception.NegocioException;
import com.jvmausa.algafood.domain.model.Cidade;
import com.jvmausa.algafood.domain.repository.CidadeRepository;
import com.jvmausa.algafood.domain.service.CadastroCidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	CidadeRepository cidadeRepository;

	@Autowired
	private CadastroCidadeService cadastroCidade;
	
	@Autowired
	private CidadeModelAssembler cidadeModelAssembler;

	@GetMapping
	public List<CidadeModel> listar() {
		return cidadeModelAssembler.toCollectionModel(cidadeRepository.findAll());

	}

	@GetMapping("/{id}")
	public CidadeModel buscar(@PathVariable Long id) {
		Cidade cidade = cadastroCidade.buscarOuFalhar(id);
		return cidadeModelAssembler.toModel(cidade);
	}

	@PostMapping
	public Cidade adicionar(@RequestBody @Valid Cidade cidade) {
		try {
			return cadastroCidade.salvar(cidade);

		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e); // exception para http 409 bad request

		}

	}

	@PutMapping("/{id}")
	public Cidade atualizar(@PathVariable Long id, @RequestBody @Valid Cidade cidade) {
		try {
			Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(id);

			BeanUtils.copyProperties(cidade, cidadeAtual, "id");

			return cadastroCidade.salvar(cidadeAtual);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e); // exception para http bad request

		}

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	private void remover(@PathVariable Long id) {
		cadastroCidade.excluir(id);
	}

}
