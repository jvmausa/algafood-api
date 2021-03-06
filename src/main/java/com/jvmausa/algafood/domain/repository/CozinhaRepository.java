package com.jvmausa.algafood.domain.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.jvmausa.algafood.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long> {

	Page<Cozinha> findTodosByNome(String nome, Pageable pageable); // exemplo com PAGINAÇÃO
	
	List<Cozinha> findTodosByNome(String nome); // pesquisa todas por nome exato
	
	List<Cozinha> findTodosByNomeContaining(String nome); // por nome contendo
	
	boolean existsByNome(String nome); // retorna true or false


}
