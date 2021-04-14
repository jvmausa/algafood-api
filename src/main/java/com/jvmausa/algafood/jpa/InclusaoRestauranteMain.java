package com.jvmausa.algafood.jpa;

import java.math.BigDecimal;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.jvmausa.algafood.AlgafoodApiApplication;
import com.jvmausa.algafood.domain.model.Restaurante;
import com.jvmausa.algafood.domain.repository.RestauranteRepository;

public class InclusaoRestauranteMain {

	public static void main(String[] args) {
		
		ApplicationContext applcationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE).run(args);
		
		RestauranteRepository restauranteRepository = applcationContext.getBean(RestauranteRepository.class);
		
		Restaurante restaurante = new Restaurante();
		restaurante.setId(3L);
		restaurante.setTaxaFrete(new BigDecimal(7.90));
		restaurante.setNome("Thailand Foodrish"); 
		
		restauranteRepository.salvar(restaurante);
		
		

	}

}
