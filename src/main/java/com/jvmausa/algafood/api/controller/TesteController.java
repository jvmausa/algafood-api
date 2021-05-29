package com.jvmausa.algafood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jvmausa.algafood.domain.model.Cozinha;
import com.jvmausa.algafood.domain.model.Restaurante;
import com.jvmausa.algafood.domain.repository.CozinhaRepository;
import com.jvmausa.algafood.domain.repository.RestauranteRepository;

@RestController
@RequestMapping("/teste")
public class TesteController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private RestauranteRepository restauranteRepository;

	@GetMapping("/cozinhas/por-nome")
	public List<Cozinha> cozinhasPorNome(String nome) {
		return cozinhaRepository.findTodosByNome(nome);

	}

	@GetMapping("/cozinhas/por-nome-contendo")
	public List<Cozinha> cozinhaPorNomeContendo(String nome) {
		return cozinhaRepository.findTodosByNomeContaining(nome);

	}
	
	@GetMapping("/cozinhas/primeiro")
	public Optional<Cozinha> cozinhaPrimeiro(){
		return cozinhaRepository.buscarPrimeiro();		
		
	}

	@GetMapping("/restaurantes/por-taxa-frete")
	public List<Restaurante> restaurantesPorTaxaFrete(BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);

	}

	@GetMapping("/restaurantes/por-nome")
	public List<Restaurante> restaurantesPorNome(String nome, Long cozinhaId) {
		return restauranteRepository.findByNomeContainingAndCozinhaId(nome, cozinhaId);

	}

	@GetMapping("/restaurantes/consultar-por-nome")
	public Restaurante consultarPorNome(String nome) {

		return restauranteRepository.consultarPorNome(nome);

	}

	@GetMapping("/restaurantes/por-nome-e-frete")
	public List<Restaurante> restaurantePorNomeFrete(String nome, BigDecimal taxaFreteInicial,
			BigDecimal taxaFreteFinal) {
		return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);

	}

	@GetMapping("/restaurantes/count-por-cozinha")
	public int restaurantesCountPorCozinha(Long cozinhaId) {
		return restauranteRepository.countByCozinhaId(cozinhaId);

	}

	@GetMapping("/restaurantes/com-frete-gratis")
	public List<Restaurante> restaurantesComFrenteGratis(String nome) {
		return restauranteRepository.findComFrenteGratis(nome);

	}
	@GetMapping("/restaurantes/primeiro")
	public Optional<Restaurante> restaurantePrimeiro(){
		return restauranteRepository.buscarPrimeiro();		
		
	}
	

}
