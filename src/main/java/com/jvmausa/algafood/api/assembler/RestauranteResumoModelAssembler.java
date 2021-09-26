package com.jvmausa.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.jvmausa.algafood.api.AlgaLinks;
import com.jvmausa.algafood.api.controller.RestauranteController;
import com.jvmausa.algafood.api.model.RestauranteResumoModel;
import com.jvmausa.algafood.domain.model.Restaurante;

@Component
public class RestauranteResumoModelAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteResumoModel>{

	@Autowired                                                     
	private ModelMapper modelMapper;
	
	@Autowired
    private AlgaLinks algaLinks;

	public RestauranteResumoModelAssembler() {
		super(RestauranteController.class, RestauranteResumoModel.class);
	}
	
	@Override
	public RestauranteResumoModel toModel(Restaurante restaurante) {
		
		RestauranteResumoModel restauranteResumoModel = createModelWithId(restaurante.getId(), restaurante);
		
		modelMapper.map(restaurante, restauranteResumoModel);
		
		restauranteResumoModel.add(algaLinks.linkToRestaurantes("restaurantes"));
		
		restauranteResumoModel.add(algaLinks.linkToCozinha(restaurante.getCozinha().getId()));
		
		return restauranteResumoModel;
	}
	
	@Override
	public CollectionModel<RestauranteResumoModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
		return super.toCollectionModel(entities).add(algaLinks.linkToRestaurantes());

	}

}
