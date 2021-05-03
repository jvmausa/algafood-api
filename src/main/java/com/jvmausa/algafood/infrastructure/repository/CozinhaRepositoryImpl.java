package com.jvmausa.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.jvmausa.algafood.domain.model.Cozinha;
import com.jvmausa.algafood.domain.repository.CozinhaRepository;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {

	@PersistenceContext
	private EntityManager manager;

	public List<Cozinha> listar() {

		return manager.createQuery("from Cozinha", Cozinha.class).getResultList(); // from "<classe Java>", e não tabela SQL

	}

	@Override
	public Cozinha buscar(Long id) {

		return manager.find(Cozinha.class, id);

	}

	@Transactional
	@Override
	public Cozinha salvar(Cozinha cozinha) { // método atualiza e adiciona

		return manager.merge(cozinha);

	}

	@Transactional
	@Override
	public void remover(Long id) {
		Cozinha cozinha = buscar(id);
		
		if (cozinha == null) {
			throw new EmptyResultDataAccessException(1); // esperava que tenha pelo menos uma cozinha
		}
		
		manager.remove(cozinha);

	}

}
