package com.jvmausa.algafood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.jvmausa.algafood.api.controller.RestauranteController;
import com.jvmausa.algafood.api.model.RestauranteModel;
import com.jvmausa.algafood.domain.model.Restaurante;

@Component
public class RestauranteModelAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteModel>{

	public RestauranteModelAssembler() {
		super(RestauranteController.class, RestauranteModel.class);
	}
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public RestauranteModel toModel(Restaurante restaurante) {
		
		RestauranteModel restauranteModel = createModelWithId(restaurante.getId(), restaurante);
		
		modelMapper.map(restaurante, RestauranteModel.class);
		
		restauranteModel.add(linkTo(RestauranteController.class).withRel("restaurantes"));
		
		return restauranteModel;
	}

	public List<RestauranteModel> toColletionModel(List<Restaurante> restaurantes) {

		return restaurantes.stream().map(restaurante -> toModel(restaurante)).collect(Collectors.toList());

	}

}
