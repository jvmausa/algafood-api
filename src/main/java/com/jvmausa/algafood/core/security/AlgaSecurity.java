package com.jvmausa.algafood.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import com.jvmausa.algafood.domain.repository.PedidoRepository;
import com.jvmausa.algafood.domain.repository.RestauranteRepository;

@Component
public class AlgaSecurity {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();

	}

	
	//pega ID usuário autenticado
	public Long getUsuarioId() {
		Jwt jwt = (Jwt) getAuthentication().getPrincipal();

		return jwt.getClaim("id_user");
	}

	public boolean gerenciaRestaurante(Long restauranteId) {
		if (restauranteId == null) {
			return false;
		}
		return restauranteRepository.existsResponsavel(restauranteId, getUsuarioId());
	}

	public boolean gerenciaRestauranteDoPedido(String codigoPedido) {
		return pedidoRepository.isPedidoGerenciadoPor(codigoPedido, getUsuarioId());
	}

	public boolean usuarioAutenticadoIgual(Long usuarioId) {
		
		return getUsuarioId() != null && usuarioId != null
				&& getUsuarioId().equals(usuarioId);
	}
	
	//verifica se existe a authority
	public boolean hasAuthority(String authorityName) {
		return getAuthentication().getAuthorities().stream()
				.anyMatch(authority -> authority.getAuthority().equals(authorityName));
	}
	
	public boolean podeGerenciarPedidos(String codigoPedido) {
		
		return hasAuthority("SCOPE_WRITE") && (hasAuthority("GERENCIAR_PEDIDOS")
				|| gerenciaRestauranteDoPedido(codigoPedido));
				
				
		
	}
	
}
