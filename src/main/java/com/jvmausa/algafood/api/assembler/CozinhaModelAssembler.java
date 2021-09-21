package com.jvmausa.algafood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.jvmausa.algafood.api.controller.CozinhaController;
import com.jvmausa.algafood.api.model.CozinhaModel;
import com.jvmausa.algafood.domain.model.Cozinha;

@Component
public class CozinhaModelAssembler extends RepresentationModelAssemblerSupport<Cozinha, CozinhaModel> {

	public CozinhaModelAssembler() {
		super(CozinhaController.class, CozinhaModel.class);

	}

	@Autowired
	private ModelMapper modelMapper;

	
	@Override
	public CozinhaModel toModel(Cozinha cozinha) {

		CozinhaModel cozinhaModel = createModelWithId(cozinha.getId(), cozinha);

		modelMapper.map(cozinha, cozinhaModel);

		cozinhaModel.add(linkTo(CozinhaController.class).withRel("cozinhas"));

		return cozinhaModel;
	}

}
