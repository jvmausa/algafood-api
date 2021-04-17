package com.jvmausa.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.jvmausa.algafood.domain.model.Cidade;
import com.jvmausa.algafood.domain.repository.CidadeRepository;


@Component
public class CidadeRepositoryImpl implements CidadeRepository{

	@PersistenceContext
	EntityManager manager;
	
	
	@Override
	public List<Cidade> listar() {
		return manager.createQuery("from cidade", Cidade.class).getResultList();
	}

	@Override
	public Cidade buscar(Long id) {
		return manager.find(Cidade.class, id);
	}
	
	@Transactional
	@Override
	public Cidade salvar(Cidade cidade) {
		return manager.merge(cidade);
	}

	@Transactional
	@Override
	public void remover(Cidade cidade) {
		cidade = buscar(cidade.getId());
		manager.remove(cidade);
		
	}

}
