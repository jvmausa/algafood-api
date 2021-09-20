package com.jvmausa.algafood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.jvmausa.algafood.api.controller.CidadeController;
import com.jvmausa.algafood.api.controller.EstadoController;
import com.jvmausa.algafood.api.model.CidadeModel;
import com.jvmausa.algafood.domain.model.Cidade;

@Component
public class CidadeModelAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeModel> {

	public CidadeModelAssembler() {
		super(CidadeController.class, CidadeModel.class);

	}

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CidadeModel toModel(Cidade cidade) {
		
		CidadeModel cidadeModel = createModelWithId(cidade.getId(), cidade);
		
		modelMapper.map(cidade, cidadeModel);

		cidadeModel.add(linkTo(methodOn(CidadeController.class).listar()).withRel("cidades"));

		cidadeModel.getEstado()
				.add(linkTo(methodOn(EstadoController.class).buscar(cidadeModel.getEstado().getId())).withSelfRel());
		
		return cidadeModel;

	}

	@Override
	public CollectionModel<CidadeModel> toCollectionModel(Iterable<? extends Cidade> entities) {
		return super.toCollectionModel(entities).add(linkTo(CidadeController.class).withSelfRel());
				
	}

}
