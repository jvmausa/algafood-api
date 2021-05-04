package com.jvmausa.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.jvmausa.algafood.AlgafoodApiApplication;
import com.jvmausa.algafood.domain.model.Cidade;
import com.jvmausa.algafood.domain.model.Estado;
import com.jvmausa.algafood.domain.repository.CidadeRepository;

public class InclusaoCidadeMain {

	public static void main(String[] args) {

		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);

		CidadeRepository cidadeRepository = applicationContext.getBean(CidadeRepository.class);
	
		
		Cidade cidade = new Cidade();
		cidade.setId(3L);
		cidade.setNome("São Paulo");
		//cidade.setEstado(new Estado().setNome("São Paulo"));
		
		cidadeRepository.salvar(cidade);
		
		

	}

}
