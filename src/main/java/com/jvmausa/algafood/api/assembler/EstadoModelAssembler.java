package com.jvmausa.algafood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.jvmausa.algafood.api.controller.EstadoController;
import com.jvmausa.algafood.api.model.EstadoModel;
import com.jvmausa.algafood.domain.model.Estado;

@Component
public class EstadoModelAssembler extends RepresentationModelAssemblerSupport<Estado, EstadoModel>{

	public EstadoModelAssembler() {
		super(EstadoController.class, EstadoModel.class);
		
	}

	@Autowired
	private ModelMapper modelMapper;

	public EstadoModel toModel(Estado estado) {
		
		EstadoModel estadoModel = createModelWithId(estado.getId(), estado);
		
		modelMapper.map(estado, estadoModel);

		estadoModel.add(linkTo(methodOn(EstadoController.class).listar()).withRel("estados"));
		
		return estadoModel;
	}

	public CollectionModel<EstadoModel> toColletionModel(Iterable<? extends Estado> entities) {
		return super.toCollectionModel(entities).add(linkTo(EstadoController.class).withSelfRel());
	}

}
