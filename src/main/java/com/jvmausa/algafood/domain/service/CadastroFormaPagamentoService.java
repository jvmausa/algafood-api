package com.jvmausa.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jvmausa.algafood.domain.exception.EntidadeEmUsoException;
import com.jvmausa.algafood.domain.exception.FormaPagamentoNaoEncontradaException;
import com.jvmausa.algafood.domain.model.FormaPagamento;
import com.jvmausa.algafood.domain.repository.FormaPagamentoRepository;

@Service
public class CadastroFormaPagamentoService {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	private static final String MSG_FORMA_PAGAMENTO_EM_USO = "Forma de pagamento de código %d não pode ser removida, pois está em uso";

	public FormaPagamento buscarOuFalhar(Long id) {
		return formaPagamentoRepository.findById(id).orElseThrow(() -> new FormaPagamentoNaoEncontradaException(id));

	}

	@Transactional
	public FormaPagamento salvar(FormaPagamento formaPagamento) {
		return formaPagamentoRepository.save(formaPagamento);

	}

	@Transactional
	public void excluir(Long id) {

		try {
			formaPagamentoRepository.deleteById(id);
			formaPagamentoRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new FormaPagamentoNaoEncontradaException(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_FORMA_PAGAMENTO_EM_USO, id));
		}

	}
}
