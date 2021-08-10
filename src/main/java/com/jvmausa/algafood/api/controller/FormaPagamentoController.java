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

import com.jvmausa.algafood.api.assembler.FormaPagamentoInputDisassembler;
import com.jvmausa.algafood.api.assembler.FormaPagamentoModelAssembler;
import com.jvmausa.algafood.api.model.FormaPagamentoModel;
import com.jvmausa.algafood.api.model.input.FormaPagamentoInput;
import com.jvmausa.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.jvmausa.algafood.domain.exception.NegocioException;
import com.jvmausa.algafood.domain.model.FormaPagamento;
import com.jvmausa.algafood.domain.repository.FormaPagamentoRepository;
import com.jvmausa.algafood.domain.service.CadastroFormaPagamentoService;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamento;

	@Autowired
	private FormaPagamentoModelAssembler formaPagamentoModelAssembler;

	@Autowired
	private FormaPagamentoInputDisassembler formaPagamentoInputDisasembler;

	
	@GetMapping
	public List<FormaPagamentoModel> listar() {
		return formaPagamentoModelAssembler.toCollectionModel(formaPagamentoRepository.findAll());

	}
	
	@GetMapping("/{id}")
	public FormaPagamentoModel buscar(@PathVariable Long id) {
		FormaPagamento formaPagamento = cadastroFormaPagamento.buscarOuFalhar(id);

		return formaPagamentoModelAssembler.toModel(formaPagamento);

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamentoModel adicionar(@RequestBody @Valid FormaPagamentoInput formaPagantoInput) {
		FormaPagamento formaPagamento = formaPagamentoInputDisasembler.toDomainObject(formaPagantoInput);

		try {
			return formaPagamentoModelAssembler.toModel(cadastroFormaPagamento.salvar(formaPagamento));
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}

	}

	@PutMapping("/{id}")
	public FormaPagamentoModel atualizar(@PathVariable Long id,
			@RequestBody @Valid FormaPagamentoInput formaPagantoInput) {
		FormaPagamento formaPagamentoAtual = cadastroFormaPagamento.buscarOuFalhar(id);

		formaPagamentoInputDisasembler.copyToDomainObject(formaPagantoInput, formaPagamentoAtual);

		try {
			return formaPagamentoModelAssembler
					.toModel(cadastroFormaPagamento.salvar(formaPagamentoAtual));
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		cadastroFormaPagamento.excluir(id);
	}

}