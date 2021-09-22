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

import com.fasterxml.jackson.annotation.JsonView;
import com.jvmausa.algafood.api.assembler.RestauranteInputDisassembler;
import com.jvmausa.algafood.api.assembler.RestauranteModelAssembler;
import com.jvmausa.algafood.api.model.RestauranteModel;
import com.jvmausa.algafood.api.model.input.RestauranteInput;
import com.jvmausa.algafood.api.model.view.RestauranteView;
import com.jvmausa.algafood.api.springfox.controller.RestauranteControllerOpenApi;
import com.jvmausa.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.jvmausa.algafood.domain.exception.NegocioException;
import com.jvmausa.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.jvmausa.algafood.domain.model.Restaurante;
import com.jvmausa.algafood.domain.repository.RestauranteRepository;
import com.jvmausa.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController implements RestauranteControllerOpenApi {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;

	@Autowired
	private RestauranteModelAssembler restauranteModelAssembler;

	@Autowired
	private RestauranteInputDisassembler restauranteInputDisasembler;

	
	
	@GetMapping(params = "projecao=completo")
	public List<RestauranteModel> listar() {
		return restauranteModelAssembler.
				toColletionModel(restauranteRepository.findAll());
	}
	
	@Override
	@JsonView(RestauranteView.Resumo.class)
	@GetMapping
	public List<RestauranteModel> listarResumido() {
		return listar();

	}

	@Override
	@GetMapping("/{restauranteId}")
	public RestauranteModel buscar(@PathVariable Long restauranteId) {
		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
		
		return restauranteModelAssembler.toModel(restaurante);
	}
	
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteModel adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {

		Restaurante restaurante = restauranteInputDisasembler.toDomainObject(restauranteInput);

		try {
			return restauranteModelAssembler.toModel(cadastroRestaurante.salvar(restaurante));

		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage()); // exception para http 409 bad request

		}

	}

	@Override
	@PutMapping("/{id}")
	public RestauranteModel atualizar(@PathVariable Long id, @RequestBody @Valid RestauranteInput restauranteInput) {
		//Restaurante restaurante = restauranteInputDisasembler.toDomainObject(restauranteInput);

		Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(id);

		restauranteInputDisasembler.copyToDomainObject(restauranteInput, restauranteAtual);

		try {
			return restauranteModelAssembler.toModel(cadastroRestaurante.salvar(restauranteAtual));

		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage()); // exception para http 409 bad request

		}

	}
	
	@Override
	@PutMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativar(@PathVariable Long id) {
		cadastroRestaurante.ativar(id);
		
	}
	
	@Override
	@DeleteMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativar(@PathVariable Long id) {
		cadastroRestaurante.inativar(id);
		
	}
	
	@Override
	@PutMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativarMultiplos(@RequestBody List<Long> restauranteIds) {
		
		try {
			cadastroRestaurante.ativarEmMassa(restauranteIds);
		} catch (RestauranteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(),e);
		}
		
	}
	
	@Override
	@DeleteMapping("/inativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativarMultiplos(@RequestBody List<Long> restauranteIds) {
		
		try {
			cadastroRestaurante.inativarEmMassa(restauranteIds);
		} catch (RestauranteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(),e);
		}
		
	}
	
	@Override
	@PutMapping("/{id}/abertura")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void abrir(@PathVariable Long id) {
	    cadastroRestaurante.abrir(id);
	}

	@Override
	@PutMapping("/{id}/fechamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void fechar(@PathVariable Long id) {
	    cadastroRestaurante.fechar(id);
	}  
	
}
