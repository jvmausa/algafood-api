package com.jvmausa.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.jvmausa.algafood.domain.exception.EntidadeEmUsoException;
import com.jvmausa.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.jvmausa.algafood.domain.model.Estado;
import com.jvmausa.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

	private static final String MSG_NÃO_PODE_SER_REMOVIDO_ESTADO = "Estado de código %d não pode ser removido pois está em uso";
	private static final String MSG_NÂO_EXISTE_ESTADO = "Não existe cadastro de Estado com esse %d";
	@Autowired
	EstadoRepository estadoRepository;
	
	
	public Estado buscarOuFalhar(Long id) {
		return estadoRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format(MSG_NÂO_EXISTE_ESTADO, id)));
		
	}

	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);

	}

	public void excluir(Long id) {

		try {

			estadoRepository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format(MSG_NÂO_EXISTE_ESTADO, id));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_NÃO_PODE_SER_REMOVIDO_ESTADO, id));
		}

	}

}
