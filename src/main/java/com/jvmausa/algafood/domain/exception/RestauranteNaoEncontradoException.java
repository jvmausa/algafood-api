package com.jvmausa.algafood.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public RestauranteNaoEncontradoException(String mensagem) {
		super(mensagem);

	}
	
	public RestauranteNaoEncontradoException(Long id) {
		this(String.format("Não existe cadastro de Restaurante com esse id %d", id));
		
		
	}

}
