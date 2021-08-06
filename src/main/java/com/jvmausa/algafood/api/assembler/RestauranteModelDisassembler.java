package com.jvmausa.algafood.api.assembler;

import org.springframework.stereotype.Component;

import com.jvmausa.algafood.api.model.input.RestauranteInput;
import com.jvmausa.algafood.domain.model.Cozinha;
import com.jvmausa.algafood.domain.model.Restaurante;

@Component
public class RestauranteModelDisassembler {

	/*
	 * método para tranformar de DTO para entity
	 * 
	 * */

	public Restaurante toDomainObject(RestauranteInput restauranteInput) {
		Restaurante restaurante = new Restaurante();
		restaurante.setNome(restauranteInput.getNome());
		restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());

		Cozinha cozinha = new Cozinha();
		cozinha.setId(restauranteInput.getCozinha().getId());

		restaurante.setCozinha(cozinha);

		return restaurante;
	}
	
}
