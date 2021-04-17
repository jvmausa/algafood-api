package com.jvmausa.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.jvmausa.algafood.AlgafoodApiApplication;
import com.jvmausa.algafood.domain.model.Permissao;
import com.jvmausa.algafood.domain.repository.PermissaoRepository;

public class AlteracaoPermissaoMain {

	public static void main(String[] args) {

		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE).run(args);

		PermissaoRepository permissaoRepository = applicationContext.getBean(PermissaoRepository.class);

		Permissao permissao = new Permissao();
		permissao.setId(1L);
		permissao.setNome("RESTAURANTE_VENDEDOR");
		permissao.setDescricao("Permitido resgatar compras");

		permissaoRepository.salvar(permissao);

	}

}
