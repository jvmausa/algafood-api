package com.jvmausa.algafood.api.v2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
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

import com.jvmausa.algafood.api.springfox.controller.v2.CozinhaControllerV2OpenApi;
import com.jvmausa.algafood.api.v2.assembler.CozinhaInputDisassemblerV2;
import com.jvmausa.algafood.api.v2.assembler.CozinhaModelAssemblerV2;
import com.jvmausa.algafood.api.v2.model.CozinhaModelV2;
import com.jvmausa.algafood.api.v2.model.input.CozinhaInputV2;
import com.jvmausa.algafood.domain.model.Cozinha;
import com.jvmausa.algafood.domain.repository.CozinhaRepository;
import com.jvmausa.algafood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("/v2/cozinhas")
public class CozinhaControllerV2 implements CozinhaControllerV2OpenApi {

	@Autowired
	private CadastroCozinhaService cadastroCozinha;

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CozinhaModelAssemblerV2 cozinhaModelAssembler;

	@Autowired
	private CozinhaInputDisassemblerV2 cozinhaInputDisassembler;

	@Autowired
	private PagedResourcesAssembler<Cozinha> pagedResourceAssembler;

	@GetMapping
	public PagedModel<CozinhaModelV2> listar(@PageableDefault(size = 2) Pageable pageable) {
		Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);

		PagedModel<CozinhaModelV2> cozinhasPagedModel = pagedResourceAssembler.toModel(cozinhasPage,
				cozinhaModelAssembler);

		return cozinhasPagedModel;
	}

	@GetMapping("/{id}")
	public CozinhaModelV2 buscar(@PathVariable Long id) {
		Cozinha cozinha = cadastroCozinha.buscarOuFalhar(id);

		return cozinhaModelAssembler.toModel(cozinha);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CozinhaModelV2 adicionar(@RequestBody @Valid CozinhaInputV2 cozinhaInput) {

		Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);

		return cozinhaModelAssembler.toModel(cadastroCozinha.salvar(cozinha));

	}

	@PutMapping("/{id}")
	public CozinhaModelV2 atualizar(@PathVariable Long id, @RequestBody @Valid CozinhaInputV2 cozinhaInput) {
		Cozinha cozinhaAtual = cadastroCozinha.buscarOuFalhar(id);

		cozinhaInputDisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);

		return cozinhaModelAssembler.toModel(cadastroCozinha.salvar(cozinhaAtual));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		cadastroCozinha.excluir(id);
	}

}
