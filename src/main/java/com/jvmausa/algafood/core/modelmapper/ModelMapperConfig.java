package com.jvmausa.algafood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jvmausa.algafood.api.model.EnderecoModel;
import com.jvmausa.algafood.domain.model.Endereco;

@Configuration
public class ModelMapperConfig {

	/*
	 * exemplo de customização de mapeamento de propriedade
	 */

	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		
		/*
		 * exemplo quando o nome dos atributos são diferentes
		modelMapper.createTypeMap(Restaurante.class, RestauranteModel.class)
		.addMapping(Restaurante::getTaxaFrete, RestauranteModel::setPrecoFrete);
		*/
		
		var enderecoModelTypeMap = modelMapper.createTypeMap(Endereco.class, EnderecoModel.class);
		
		// criação de mapapemaneto de Endereco para EnderecoModel
		enderecoModelTypeMap.<String>addMapping(
				enderecoModelSource -> enderecoModelSource.getCidade().getEstado().getNome(), 
				(enderecoModelDest, value) -> enderecoModelDest.getCidade().setNomeEstado(value));
		
		return modelMapper;

	}

}
