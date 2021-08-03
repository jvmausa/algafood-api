package com.jvmausa.algafood;

import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jvmausa.algafood.domain.exception.EntidadeEmUsoException;
import com.jvmausa.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.jvmausa.algafood.domain.model.Cozinha;
import com.jvmausa.algafood.domain.service.CadastroCozinhaService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CadastroCozinhaIntegrationTests {

	@Autowired
	CadastroCozinhaService cadastroCozinha;

	@Test
	public void testarCadastroCozinhaComSucesso() {
		// cenário
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Chinesa");

		// ação
		novaCozinha = cadastroCozinha.salvar(novaCozinha);

		// validação
		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();

	}

	@Test
	public void testarCadastroCozinhaSemNome() {
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome(null);

		ConstraintViolationException erroEspero = 
				Assertions.assertThrows(ConstraintViolationException.class, () -> {
			cadastroCozinha.salvar(novaCozinha);
		});

		assertThat(erroEspero).isNotNull();
	}
	
	@Test
	public void deveFalhar_ExcluirCozinhaEmUso() {
		EntidadeEmUsoException erroEsperado = 
				Assertions.assertThrows(EntidadeEmUsoException.class, () -> {
					cadastroCozinha.excluir(1L);
				});
			
		assertThat(erroEsperado).isNotNull();
		System.out.println(erroEsperado);
	}
	
	@Test
	public void deveFalhar_ExcluirCozinhaInexistente() {
		EntidadeNaoEncontradaException erroEsperado = 
				Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> {
					cadastroCozinha.excluir(10L);
				});
		
		assertThat(erroEsperado).isNotNull();
		System.out.println(erroEsperado);
		
	}
	

}
