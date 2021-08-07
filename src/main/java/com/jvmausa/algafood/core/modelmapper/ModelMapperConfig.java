package com.jvmausa.algafood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jvmausa.algafood.api.model.RestauranteModel;
import com.jvmausa.algafood.domain.model.Restaurante;

@Configuration
public class ModelMapperConfig {

	/*
	 * exemplo de customização de mapeamento de propriedade
	 */
	
	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		
		modelMapper.createTypeMap(Restaurante.class, RestauranteModel.class)
		.addMapping(Restaurante::getTaxaFrete, RestauranteModel::setPrecoFrete);
		
		return modelMapper;
	}
	
	
	
	
}
