package com.jvmausa.algafood.domain.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public CidadeNaoEncontradaException(String mensagem) {
		super(mensagem);

	}
	
	public CidadeNaoEncontradaException(Long id) {
		this(String.format("Não existe cadastro de Cidade com esse id %d", id));
		
		
	}

}
