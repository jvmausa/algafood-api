package com.jvmausa.algafood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jvmausa.algafood.api.v1.model.EnderecoModel;
import com.jvmausa.algafood.api.v1.model.input.ItemPedidoInput;
import com.jvmausa.algafood.api.v2.model.input.CidadeInputV2;
import com.jvmausa.algafood.domain.model.Cidade;
import com.jvmausa.algafood.domain.model.Endereco;
import com.jvmausa.algafood.domain.model.ItemPedido;

@Configuration
public class ModelMapperConfig {

	/*
	 * exemplo de customização de mapeamento de propriedade
	 */

	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		
		//quando estiver convertendo de cidadeInputV2 para Cidade.class, pode ignorar(skip) no Cidade.setId()
		modelMapper.createTypeMap(CidadeInputV2.class, Cidade.class)
	    .addMappings(mapper -> mapper.skip(Cidade::setId));  
		
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
		
		modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
	    .addMappings(mapper -> mapper.skip(ItemPedido::setId));  
		
		return modelMapper;

	}

}
