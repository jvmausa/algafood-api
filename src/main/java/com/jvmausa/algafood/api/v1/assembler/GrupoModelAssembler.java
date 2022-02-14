package com.jvmausa.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.jvmausa.algafood.api.v1.AlgaLinks;
import com.jvmausa.algafood.api.v1.controller.GrupoController;
import com.jvmausa.algafood.api.v1.model.GrupoModel;
import com.jvmausa.algafood.domain.model.Grupo;

@Component
public class GrupoModelAssembler extends RepresentationModelAssemblerSupport<Grupo, GrupoModel>{

	public GrupoModelAssembler() {
		super(GrupoController.class, GrupoModel.class);
	}

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	AlgaLinks algaLinks;
	
	@Override
	public GrupoModel toModel(Grupo grupo) {
		
		GrupoModel grupoModel = createModelWithId(grupo.getId(), grupo);
		
		modelMapper.map(grupo, grupoModel);

		grupoModel.add(algaLinks.linkToGrupos("grupos"));
		grupoModel.add(algaLinks.linkToGrupoPermissoes(grupo.getId(), "grupo-permissoes"));
		
		return grupoModel;
	}
	
	@Override
	public CollectionModel<GrupoModel> toCollectionModel(Iterable<? extends Grupo> entities){
		return super.toCollectionModel(entities).add(algaLinks.linkToGrupos());
		
	}

}
