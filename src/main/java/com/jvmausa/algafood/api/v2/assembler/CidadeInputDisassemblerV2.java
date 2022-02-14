package com.jvmausa.algafood.api.v2.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jvmausa.algafood.api.v2.model.input.CidadeInputV2;
import com.jvmausa.algafood.domain.model.Cidade;

@Component
public class CidadeInputDisassemblerV2 {

	@Autowired
	private ModelMapper modelMapper;

	public Cidade toDomainObject(CidadeInputV2 cidadeInput) {
		return modelMapper.map(cidadeInput, Cidade.class);
	}

	public void copyToDomainObject(CidadeInputV2 cidadeInput, Cidade cidade) {
		modelMapper.map(cidadeInput, cidade);

	}

}
