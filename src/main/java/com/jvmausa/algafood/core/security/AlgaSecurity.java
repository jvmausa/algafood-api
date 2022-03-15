package com.jvmausa.algafood.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import com.jvmausa.algafood.domain.repository.RestauranteRepository;

@Component
public class AlgaSecurity {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	

	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();

	}

	public Long getUsuarioId() {
		Jwt jwt = (Jwt) getAuthentication().getPrincipal();

		return jwt.getClaim("id_user");
	}

	public boolean gerenciaRestaurante(Long restauranteId) {
		return restauranteRepository.existsResponsavel(restauranteId, getUsuarioId());
	}

}
