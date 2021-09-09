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

import com.jvmausa.algafood.api.assembler.CidadeInputDisassembler;
import com.jvmausa.algafood.api.assembler.CidadeModelAssembler;
import com.jvmausa.algafood.api.exceptionhandler.Problem;
import com.jvmausa.algafood.api.model.CidadeModel;
import com.jvmausa.algafood.api.model.input.CidadeInput;
import com.jvmausa.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.jvmausa.algafood.domain.exception.NegocioException;
import com.jvmausa.algafood.domain.model.Cidade;
import com.jvmausa.algafood.domain.repository.CidadeRepository;
import com.jvmausa.algafood.domain.service.CadastroCidadeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cidades")
@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	CidadeRepository cidadeRepository;

	@Autowired
	private CadastroCidadeService cadastroCidade;

	@Autowired
	private CidadeModelAssembler cidadeModelAssembler;

	@Autowired
	private CidadeInputDisassembler cidadeInputDisassembler;

	@ApiOperation("Lista cidades")
	@GetMapping
	public List<CidadeModel> listar() {
		return cidadeModelAssembler.toCollectionModel(cidadeRepository.findAll());

	}

	@ApiOperation("Busca cidade por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da cidade Inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
	})
	@GetMapping("/{id}")
	public CidadeModel buscar(@ApiParam(value = "ID de uma Cidade", example = "1") @PathVariable Long id) {
		Cidade cidade = cadastroCidade.buscarOuFalhar(id);
		return cidadeModelAssembler.toModel(cidade);
	}

	@ApiOperation("Adiciona uma nova cidade")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Cidade cadastrada")
	})
	@PostMapping
	public CidadeModel adicionar(@ApiParam(name = "corpo", value = "Representação de uma nova cidade") @RequestBody @Valid CidadeInput cidadeInput) {

		Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);

		try {
			return cidadeModelAssembler.toModel(cadastroCidade.salvar(cidade));

		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e); // exception para http 409 bad request

		}

	}
	@ApiOperation("Atualiza uma cidade por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cidade atualizada")
	})
	@PutMapping("/{id}")
	public CidadeModel atualizar(@ApiParam(value = "IDde uma Cidade") @PathVariable Long id, 
			@ApiParam(name = "corpo", value = "Representação de uma cidade com novos dados") 
			@RequestBody @Valid CidadeInput cidadeInput) {
			
		Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(id);
		cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);
		
		try {
			return cidadeModelAssembler.toModel(cadastroCidade.salvar(cidadeAtual));
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e); // exception para http bad request

		}

	}
	
	@ApiOperation("Deleta uma cidade por ID")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	private void remover(@ApiParam(value = "ID de uma Cidade") @PathVariable Long id) {
		cadastroCidade.excluir(id);
	}

}
