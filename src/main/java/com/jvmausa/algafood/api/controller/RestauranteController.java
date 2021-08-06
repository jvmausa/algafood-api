package com.jvmausa.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jvmausa.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.jvmausa.algafood.domain.exception.NegocioException;
import com.jvmausa.algafood.domain.model.Restaurante;
import com.jvmausa.algafood.domain.repository.RestauranteRepository;
import com.jvmausa.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;

	@GetMapping
	public List<Restaurante> listar() {
		return restauranteRepository.findAll();

	}

	@GetMapping("/{id}")
	public Restaurante buscar(@PathVariable Long id) {
		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(id);

		return restaurante;

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurante adicionar(@RequestBody @Valid Restaurante restaurante) {

		try {
			return cadastroRestaurante.salvar(restaurante);

		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage()); // exception para http 409 bad request

		}

	}

	@PutMapping("/{id}")
	public Restaurante atualizar(@PathVariable Long id, @RequestBody @Valid Restaurante restaurante) {
		Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(id);

		BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro",
				"produtos");

		try {
			return cadastroRestaurante.salvar(restauranteAtual);

		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage()); // exception para http 409 bad request

		}

	}

}
