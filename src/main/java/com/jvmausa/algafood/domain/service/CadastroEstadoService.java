package com.jvmausa.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jvmausa.algafood.domain.exception.EntidadeEmUsoException;
import com.jvmausa.algafood.domain.exception.EstadoNaoEncontradoException;
import com.jvmausa.algafood.domain.model.Estado;
import com.jvmausa.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

	private static final String MSG_NÃO_PODE_SER_REMOVIDO_ESTADO = "Estado de código %d não pode ser removido pois está em uso";
	
	@Autowired
	EstadoRepository estadoRepository;
	
	
	public Estado buscarOuFalhar(Long id) {
		return estadoRepository.findById(id)
				.orElseThrow(() -> new EstadoNaoEncontradoException(id));
		
	}

	@Transactional
	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);

	}
	
	@Transactional
	public void excluir(Long id) {
		try {

			estadoRepository.deleteById(id);
			estadoRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradoException(id);
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_NÃO_PODE_SER_REMOVIDO_ESTADO, id));
		}

	}

}
