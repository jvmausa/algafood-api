package com.jvmausa.algafood.api.controller;

import java.math.BigDecimal;
import java.util.List;

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
	public List<Cozinha> cozinhasPorNome(String nome){
		
		return cozinhaRepository.findTodosByNome(nome);
		
	}
	
	@GetMapping("/cozinhas/por-nome-contendo")
	public List<Cozinha> cozinhaPorNomeContendo(String nome){
		
		return cozinhaRepository.findTodosByNomeContaining(nome);
		
	}
	

	@GetMapping("/restaurantes/por-taxa-frete")
	public List<Restaurante> restaurantesPorTaxaFrete(BigDecimal taxaInicial, BigDecimal taxaFinal){
		
		return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
		
	}
	
	@GetMapping("/restaurantes/por-nome")
	public List<Restaurante> restaurantesPorNome(String nome, Long cozinhaId){
		
		return restauranteRepository.findByNomeContainingAndCozinhaId(nome, cozinhaId);
		
	}
	
	
	
}
