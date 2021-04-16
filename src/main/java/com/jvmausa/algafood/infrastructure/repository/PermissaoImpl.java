package com.jvmausa.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.jvmausa.algafood.domain.model.Permissao;
import com.jvmausa.algafood.domain.repository.PermissaoRepository;

@Component
public class PermissaoImpl implements PermissaoRepository {

	@PersistenceContext
	EntityManager manager;

	@Override
	public List<Permissao> listar() {
		return manager.createQuery("from permissao", Permissao.class).getResultList();
	}

	@Override
	public Permissao buscar(Long Id) {
		return manager.find(Permissao.class, Id);
	}

	@Override
	public Permissao salvar(Permissao permissao) {
		return manager.merge(permissao);
	}

	@Override
	public void remover(Permissao permissao) {
		permissao = buscar(permissao.getId());
		manager.remove(permissao);

	}

}
