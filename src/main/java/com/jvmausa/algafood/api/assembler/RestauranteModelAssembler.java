package com.jvmausa.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.jvmausa.algafood.api.model.CozinhaModel;
import com.jvmausa.algafood.api.model.RestauranteModel;
import com.jvmausa.algafood.domain.model.Restaurante;

@Component
public class RestauranteModelAssembler {


	/*
	 * classe responsável por fazer as conversões
	 * de ENTITY para DTO
	 * 
	 */

	public RestauranteModel toModel(Restaurante restaurante) {
		CozinhaModel cozinhaModel = new CozinhaModel();
		cozinhaModel.setId(restaurante.getCozinha().getId());
		cozinhaModel.setNome(restaurante.getCozinha().getNome());

		RestauranteModel restauranteModel = new RestauranteModel();
		restauranteModel.setId(restaurante.getId());
		restauranteModel.setNome(restaurante.getNome());
		restauranteModel.setTaxaFrete(restaurante.getTaxaFrete());
		restauranteModel.setCozinha(cozinhaModel);
		return restauranteModel;
	}

	public List<RestauranteModel> toColletionModel(List<Restaurante> restaurantes) {

		return restaurantes.stream().map(restaurante -> toModel(restaurante)).collect(Collectors.toList());

	}
	
}
