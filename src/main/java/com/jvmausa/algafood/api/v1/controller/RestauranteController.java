package com.jvmausa.algafood.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jvmausa.algafood.api.springfox.controller.v1.RestauranteControllerOpenApi;
import com.jvmausa.algafood.api.v1.assembler.RestauranteInputDisassembler;
import com.jvmausa.algafood.api.v1.assembler.RestauranteModelAssembler;
import com.jvmausa.algafood.api.v1.assembler.RestauranteResumoModelAssembler;
import com.jvmausa.algafood.api.v1.model.RestauranteModel;
import com.jvmausa.algafood.api.v1.model.RestauranteResumoModel;
import com.jvmausa.algafood.api.v1.model.input.RestauranteInput;
import com.jvmausa.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.jvmausa.algafood.domain.exception.NegocioException;
import com.jvmausa.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.jvmausa.algafood.domain.model.Restaurante;
import com.jvmausa.algafood.domain.repository.RestauranteRepository;
import com.jvmausa.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/v1/restaurantes")
public class RestauranteController implements RestauranteControllerOpenApi {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;

	@Autowired
	private RestauranteModelAssembler restauranteModelAssembler;

	@Autowired
	private RestauranteInputDisassembler restauranteInputDisasembler;

	 @Autowired
	 private RestauranteResumoModelAssembler restauranteResumoModelAssembler;
	
	@Override
	@GetMapping(params = "projecao=completo")
	public CollectionModel<RestauranteModel> listar() {
		return restauranteModelAssembler.
				toCollectionModel(restauranteRepository.findAll());
	}
	
	@Override
	@GetMapping
	public CollectionModel<RestauranteResumoModel> listarResumido() {
		return restauranteResumoModelAssembler.
				toCollectionModel(restauranteRepository.findAll());
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
			throw new NegocioException(e.getMessage());

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
	public ResponseEntity<Void> ativar(@PathVariable Long id) {
		cadastroRestaurante.ativar(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@Override
	@DeleteMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> inativar(@PathVariable Long id) {
		cadastroRestaurante.inativar(id);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	@PutMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> ativarMultiplos(@RequestBody List<Long> restauranteIds) {
		
		try {
			cadastroRestaurante.ativarEmMassa(restauranteIds);
			return ResponseEntity.noContent().build();
		} catch (RestauranteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(),e);
		}
		
	}
	
	@Override
	@DeleteMapping("/inativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> inativarMultiplos(@RequestBody List<Long> restauranteIds) {
		
		try {
			cadastroRestaurante.inativarEmMassa(restauranteIds);
			return ResponseEntity.noContent().build();
		} catch (RestauranteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(),e);
		}
		
	}
	
	@Override
	@PutMapping("/{id}/abertura")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> abrir(@PathVariable Long id) {
	    cadastroRestaurante.abrir(id);
	    return ResponseEntity.noContent().build();
	}

	@Override
	@PutMapping("/{id}/fechamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> fechar(@PathVariable Long id) {
	    cadastroRestaurante.fechar(id);
	    return ResponseEntity.noContent().build();
	}  
	
}
