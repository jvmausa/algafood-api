package com.jvmausa.algafood.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jvmausa.algafood.domain.exception.NegocioException;
import com.jvmausa.algafood.domain.exception.UsuarioNaoEncontradoException;
import com.jvmausa.algafood.domain.model.Grupo;
import com.jvmausa.algafood.domain.model.Usuario;
import com.jvmausa.algafood.domain.repository.UsuarioRepository;

@Service
public class CadastroUsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CadastroGrupoService cadastroGrupo;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		usuarioRepository.detach(usuario);

		Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());

		if (usuario.isNovo()) {
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		}
		
		if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new NegocioException(
					String.format("Já existe um usuário cadastrado com o e-mail %s", usuario.getEmail()));
		}

		return usuarioRepository.save(usuario);
	}

	@Transactional
	public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
		Usuario usuario = buscarOuFalhar(usuarioId);
	    
	    if (!passwordEncoder.matches(senhaAtual, usuario.getSenha())) {
	        throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
	    }
	    
	    usuario.setSenha(passwordEncoder.encode(novaSenha));
	}

	@Transactional
	public void desassociarGrupo(Long usuarioId, Long grupoId) {
		Usuario usuario = buscarOuFalhar(usuarioId);
		Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);

		usuario.removerGrupo(grupo);
	}

	@Transactional
	public void associarGrupo(Long usuarioId, Long grupoId) {
		Usuario usuario = buscarOuFalhar(usuarioId);
		Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);

		usuario.adicionarGrupo(grupo);
	}

	public Usuario buscarOuFalhar(Long usuarioId) {
		return usuarioRepository.findById(usuarioId).orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
	}
}