package com.jvmausa.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
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
		return cadastroRestaurante.buscarOuFalhar(id);

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurante adicionar(@RequestBody Restaurante restaurante) {
		return cadastroRestaurante.salvar(restaurante);		

	}

	@PutMapping("/{id}")
	public Restaurante atualizar(@PathVariable Long id, @RequestBody Restaurante restaurante) {
		Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(id);

		BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro",
				"produtos");

		return cadastroRestaurante.salvar(restauranteAtual);
	}

	@PatchMapping("/{id}")
	public Restaurante atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
		Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(id);

		merge(campos, restauranteAtual);

		return atualizar(id, restauranteAtual);

	}

	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {

		/*
		 * >objectMapper.convertValue() converte o tipo de valor que foi inserido no
		 * JSON pro valor do atribudo correspondente da classe Restaurante ex: no JSON é
		 * recebido como INTEIRO e é preciso que seja BIDECIMAL.
		 */
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

		dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
			field.setAccessible(true);

			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

			//System.out.println(nomePropriedade + " = " + valorPropriedade + " = " + novoValor);

			ReflectionUtils.setField(field, restauranteDestino, novoValor);

		});
	}
	
	/*
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	private void remover(@PathVariable Long id) {

		cadastroRestaurante.remover(id);

	}
	*/
}
