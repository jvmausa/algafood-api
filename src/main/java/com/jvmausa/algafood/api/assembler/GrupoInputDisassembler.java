package com.jvmausa.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jvmausa.algafood.api.model.input.GrupoInput;
import com.jvmausa.algafood.domain.model.Grupo;

@Component
public class GrupoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Grupo toDomainObject(GrupoInput grupoInput) {
		return modelMapper.map(grupoInput, Grupo.class);
		
	}
	
	public void copyToDomainObject(Grupo grupoInput, Grupo grupo) {
		modelMapper.map(grupoInput, grupo);
		
	}
	
}
