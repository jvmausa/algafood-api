package com.jvmausa.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jvmausa.algafood.api.model.RestauranteModel;
import com.jvmausa.algafood.domain.model.Restaurante;

@Component
public class RestauranteModelAssembler {

	/*
	 * ModelMapper substitui os gets e sets anteriormente utilizados para converter de DTO para entidade
	 * 
	 */
	
	@Autowired
	private ModelMapper modelMapper;

	/*
	 * classe responsável por fazer as conversões de domain model(entity) para representation model
	 * 
	 */

	public RestauranteModel toModel(Restaurante restaurante) {
		return modelMapper.map(restaurante, RestauranteModel.class);
	}

	public List<RestauranteModel> toColletionModel(List<Restaurante> restaurantes) {

		return restaurantes.stream().map(restaurante -> toModel(restaurante)).collect(Collectors.toList());

	}

}
