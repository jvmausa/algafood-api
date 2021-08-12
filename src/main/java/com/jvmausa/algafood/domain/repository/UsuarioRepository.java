package com.jvmausa.algafood.domain.repository;

import org.springframework.stereotype.Repository;

import com.jvmausa.algafood.domain.model.Usuario;

@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {

}