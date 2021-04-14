package com.jvmausa.algafood.domain.repository;

import java.util.List;

import com.jvmausa.algafood.domain.model.Cozinha;

public interface CozinhaRepository {
	
	
	List<Cozinha> listar();
	Cozinha buscar(Long Id);
	Cozinha salvar(Cozinha cozinha);
	void remover(Cozinha cozinha);
	
	

}
