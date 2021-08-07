package com.jvmausa.algafood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	/*
	 * exemplo de customização de mapeamento de propriedade
	 */

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();

	}

}
