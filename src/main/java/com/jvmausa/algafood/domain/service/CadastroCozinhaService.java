package com.jvmausa.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jvmausa.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.jvmausa.algafood.domain.exception.EntidadeEmUsoException;
import com.jvmausa.algafood.domain.model.Cozinha;
import com.jvmausa.algafood.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {

	private static final String MSG_COZINHA_EM_USO = "Cozinha de código %d não pode ser removida pois está em uso";
	
	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Transactional
	public Cozinha salvar(Cozinha cozinha) {

		// aqui que são colocadas regras/lógicas de negócio

		return cozinhaRepository.save(cozinha);

	}

	// aumentando o tamanho do escopo
	@Transactional
	public void excluir(Long id) {

		try {
			cozinhaRepository.deleteById(id);
			/*
			 * flush adicionado para correção de bug onde o Spring não deixava que a Exception 
			 * DataIntegrityViolationException fosse capturada, poois a anotação @Transactional faz
			 * com que só seja feito commit pelo JPA depois que tudo está executado dentro do escopo do método
			 * fazendo com que não desse tempo do try/catch fizesse o handle da exception
			 */
			cozinhaRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new CozinhaNaoEncontradaException(id);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_COZINHA_EM_USO, id));
		}

	}
	
	public Cozinha buscarOuFalhar(Long id) {
		return cozinhaRepository.findById(id)
				.orElseThrow(() -> new CozinhaNaoEncontradaException(id));
	}

}
