package com.jvmausa.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@GetMapping
	public List<Cidade> listar() {
		return cidadeRepository.findAll();

	}

	@GetMapping("/{id}")
	public Cidade buscar(@PathVariable Long id) {
		return cadastroCidade.buscarOuFalhar(id);

	}

	@PostMapping
	public Cidade adicionar(@RequestBody Cidade cidade) {
		try {
			return cadastroCidade.salvar(cidade);
			
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage()); //exception para http 409 bad request
		
		}

	}

	@PutMapping("/{id}")
	public Cidade atualizar(@PathVariable Long id, @RequestBody Cidade cidade) {
		Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(id);

		BeanUtils.copyProperties(cidade, cidadeAtual, "id");
		
		try {
			return cadastroCidade.salvar(cidadeAtual);
			
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage()); //exception para http 409 bad request
			
		}
		
	}

	@DeleteMapping("/{id}")
	private void remover(@PathVariable Long id) {
		cadastroCidade.excluir(id);
	}

}
