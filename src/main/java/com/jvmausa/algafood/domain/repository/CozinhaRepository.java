package com.jvmausa.algafood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jvmausa.algafood.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

	List<Cozinha> findTodosByNome(String nome); // pesquisa todas por nome exato
	
	List<Cozinha> findTodosByNomeContaining(String nome); // por nome contendo
	
	boolean existsByNome(String nome);


}
