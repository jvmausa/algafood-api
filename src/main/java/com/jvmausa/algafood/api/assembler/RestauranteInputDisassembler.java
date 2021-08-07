package com.jvmausa.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jvmausa.algafood.api.model.input.RestauranteInput;
import com.jvmausa.algafood.domain.model.Restaurante;

@Component
public class RestauranteInputDisassembler {

	
	/*
	 * ModelMapper substitui os gets e sets anteriormente utilizados para converter de DTO para entidade
	 * 
	 */
	@Autowired
	private ModelMapper modelMapper;
	
	/*
	 * método para tranformar de representation model para domain model(entity)
	 * 
	 * */

	public Restaurante toDomainObject(RestauranteInput restauranteInput) {
		return modelMapper.map(restauranteInput, Restaurante.class);
	}
	
}
