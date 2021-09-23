package com.jvmausa.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.jvmausa.algafood.api.AlgaLinks;
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

	@Autowired
	private AlgaLinks algaLinks;
	
	@Override
	public CozinhaModel toModel(Cozinha cozinha) {

		CozinhaModel cozinhaModel = createModelWithId(cozinha.getId(), cozinha);

		modelMapper.map(cozinha, cozinhaModel);

		cozinhaModel.add(algaLinks.linkToCozinhas("cozinhas"));

		return cozinhaModel;
	}

}
