package com.jvmausa.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jvmausa.algafood.domain.exception.NegocioException;
import com.jvmausa.algafood.domain.exception.UsuarioNaoEncontradoException;
import com.jvmausa.algafood.domain.model.Usuario;
import com.jvmausa.algafood.domain.repository.UsuarioRepository;

@Service
public class CadastroUsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	/*
	 * método salvar(void) também funciona no caso de ATUALIZAR desde que com a
	 * anotação @Transactional. Mas com ADICIONAR, não.
	 * 
	 * 
	 */

	@Transactional
	public Usuario salvar(Usuario usuario) {
		return usuarioRepository.save(usuario);

	}

	@Transactional
	public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
		Usuario usuario = buscarOuFalhar(usuarioId);

		if (usuario.senhaNaoCoincideCom(senhaAtual)) {
			throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
		}

		usuario.setSenha(novaSenha);
	}

	public Usuario buscarOuFalhar(Long usuarioId) {
		return usuarioRepository.findById(usuarioId).orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
	}
}