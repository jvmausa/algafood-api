package com.jvmausa.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.jvmausa.algafood.AlgafoodApiApplication;
import com.jvmausa.algafood.domain.model.Restaurante;
import com.jvmausa.algafood.domain.repository.RestauranteRepository;

public class ConsultaRestauranteMain {
	
	
	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE).run(args);
		
		
		RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
		
		List<Restaurante> restaurantes = restauranteRepository.listar();
		
		for(Restaurante restaunte : restaurantes) {
			System.out.println(restaunte.getNome());
			
		}
		
		
	}
	
	
	
	
	
	

}
