package com.jvmausa.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jvmausa.algafood.domain.exception.EntidadeEmUsoException;
import com.jvmausa.algafood.domain.exception.GrupoNaoEncontradoException;
import com.jvmausa.algafood.domain.model.Grupo;
import com.jvmausa.algafood.domain.repository.GrupoRepository;

@Service
public class CadastroGrupoService {

	private static final String MSG_GRUPO_EM_USO 
    = "Grupo de código %d não pode ser removido, pois está em uso";
	
	@Autowired
	private GrupoRepository grupoRepository;

	public Grupo buscarOuFalhar(Long id) {
		return grupoRepository.findById(id)
				.orElseThrow(() -> new GrupoNaoEncontradoException(id));
	}

	@Transactional
	public Grupo salvar(Grupo grupo) {
		return grupoRepository.save(grupo);
		
	}
	
	@Transactional
	public void excluir(Long id) {
		
		try {
			grupoRepository.deleteById(id);
			grupoRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new GrupoNaoEncontradoException(id);
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_GRUPO_EM_USO, id));
		}
		
	}
	
	
}
