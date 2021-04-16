package com.jvmausa.algafood.domain.repository;

import java.util.List;

import com.jvmausa.algafood.domain.model.FormaPagamento;

public interface FormaPagamentoRespository {

	List<FormaPagamento> listar();
	FormaPagamento buscar(Long id);
	FormaPagamento salvar(FormaPagamento formaPagamento);
	void remover(FormaPagamento formaPagamento);

	
}
