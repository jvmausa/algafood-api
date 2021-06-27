package com.jvmausa.algafood.domain.exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public CozinhaNaoEncontradaException(String mensagem) {
		super(mensagem);

	}
	
	public CozinhaNaoEncontradaException(Long id) {
		this(String.format("Não existe cadastro de Cozinha com esse id %d", id));
		
		
	}

}
