package com.jvmausa.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.jvmausa.algafood.AlgafoodApiApplication;
import com.jvmausa.algafood.domain.model.FormaPagamento;
import com.jvmausa.algafood.domain.repository.FormaPagamentoRepository;

public class BuscaFormaPagamentoMain {

	public static void main(String[] args) {

		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE).run(args);

		FormaPagamentoRepository formaPagamentoRepository = applicationContext.getBean(FormaPagamentoRepository.class);

		FormaPagamento formaPagamento = formaPagamentoRepository.buscar(1L);

		System.out.println(formaPagamento.getDescricao());

	}

}