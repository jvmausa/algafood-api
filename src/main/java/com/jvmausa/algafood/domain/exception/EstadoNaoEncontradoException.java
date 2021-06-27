package com.jvmausa.algafood.domain.exception;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public EstadoNaoEncontradoException(String mensagem) {
		super(mensagem);

	}
	
	public EstadoNaoEncontradoException(Long id) {
		this(String.format("Não existe cadastro de Estado com esse id %d", id));
		
		
	}

}
