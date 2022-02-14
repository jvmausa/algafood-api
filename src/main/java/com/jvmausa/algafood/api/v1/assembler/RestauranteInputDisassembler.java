package com.jvmausa.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jvmausa.algafood.api.v1.model.input.RestauranteInput;
import com.jvmausa.algafood.domain.model.Cidade;
import com.jvmausa.algafood.domain.model.Cozinha;
import com.jvmausa.algafood.domain.model.Restaurante;

@Component
public class RestauranteInputDisassembler {

	/*
	 * ModelMapper substitui os gets e sets anteriormente utilizados para converter
	 * de DTO(conjunto de atributos da classe) para entidade
	 * 
	 */
	@Autowired
	private ModelMapper modelMapper;

	/*
	 * método para tranformar de representation model para domain model(entity)
	 * 
	 */

	public Restaurante toDomainObject(RestauranteInput restauranteInput) {
		return modelMapper.map(restauranteInput, Restaurante.class);
	}

	public void copyToDomainObject(RestauranteInput restauranteInput, Restaurante restaurante) {

		/*
		 * org.springframework.orm.jpa.JpaSystemException: identifier of an instance of
		 * com.jvmausa.algafood.domain.model.Cidade was altered from 1 to 2; nested
		 * exception is org.hibernate.HibernateException: identifier of an instance of
		 * com.jvmausa.algafood.domain.model.Cidade was altered from 1 to 2
		 * 
		 * corrigido com ->
		 */
		if (restaurante.getEndereco() != null) {
			restaurante.getEndereco().setCidade(new Cidade());
		}

		restaurante.setCozinha(new Cozinha());

		modelMapper.map(restauranteInput, restaurante);

	}

}
