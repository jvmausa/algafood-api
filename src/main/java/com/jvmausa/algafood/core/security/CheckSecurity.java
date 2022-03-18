package com.jvmausa.algafood.core.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

@Retention(RUNTIME)
@Target(METHOD)
public @interface CheckSecurity {

	public @interface Cozinhas {
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_COZINHAS')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar {}

		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar {}

	}

	public @interface Restaurantes {

	    @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_RESTAURANTES')")
	    @Retention(RUNTIME)
	    @Target(METHOD)
	    public @interface PodeGerenciarCadastro { }
	    
	    @PreAuthorize("hasAuthority('SCOPE_WRITE') and "
	    		+ "(hasAuthority('EDITAR_RESTAURANTES') or "
	    		+ "@algaSecurity.gerenciaRestaurante(#restauranteId))") //variavel #id é viariável no Controller
	    @Retention(RUNTIME)
	    @Target(METHOD)
	    public @interface PodeGerenciarFuncionamento { }

	    @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
	    @Retention(RUNTIME)
	    @Target(METHOD)
	    public @interface PodeConsultar { }
	    
	}
	
	public @interface Pedidos {

	    @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
	    @PostAuthorize("hasAuthority('CONSULTAR_PEDIDOS') or"
	    		+ "@algaSecurity.usuarioAutenticadoIgual(returnObject.cliente.id) or" //se usuário autenticado na requisição for o mesmo cliente do pedido, autoriza
	    		+ "@algaSecurity.gerenciaRestaurante(returnObject.restaurante.id)") 
	    @Retention(RUNTIME)
	    @Target(METHOD)
	    public @interface PodeBuscar { }
	    
	    @PreAuthorize("hasAuthority('SCOPE_READ') and (hasAuthority('CONSULTAR_PEDIDOS') or "
	    		+ "@algaSecurity.usuarioAutenticadoIgual(#filtro.clienteId) or "
	    		+ "@algaSecurity.gerenciaRestaurante(#filtro.restauranteId))")
	    @Retention(RUNTIME)
	    @Target(METHOD)
	    public @interface PodePesquisar { }
	    
	    @PreAuthorize("hasAuthority('SCOPE_WRITE') and isAuthenticated()")
	    @Retention(RUNTIME)
	    @Target(METHOD)
	    public @interface PodeCriar { }
	    
	    @PreAuthorize("hasAuthority('SCOPE_READ') and (hasAuthority('GERENCIAR_PEDIDOS') or "
	    		+ "@algaSecurity.gerenciaRestauranteDoPedido(#codigoPedido))")
	    @Retention(RUNTIME)
	    @Target(METHOD)
	    public @interface PodeGerenciarPedidos { }
	    
	}
	
	public @interface FormaPagamentos {
		
		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@Retention(RUNTIME)
	    @Target(METHOD)
		public @interface PodeConsultar { }
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_FORMAS_PAGAMENTO')")
		@Retention(RUNTIME)
	    @Target(METHOD)
		public @interface PodeEditar { }
		
	}
		
	public @interface Cidades {
		
		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@Retention(RUNTIME)
	    @Target(METHOD)
		public @interface PodeConsultar { } 
		
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_CIDADES')")
		@Retention(RUNTIME)
	    @Target(METHOD)
		public @interface PodeEditar { }
		
	}
	
	public @interface Estados {
		
		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@Retention(RUNTIME)
	    @Target(METHOD)
		public @interface PodeConsultar { } 
		
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_ESTADOS')")
		@Retention(RUNTIME)
	    @Target(METHOD)
		public @interface PodeEditar { }
		
	}
	
	public @interface UsuariosGruposPermissoes {
		
		@PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('CONSULTAR_USUARIOS_GRUPOS_PERMISSOES')")
		@Retention(RUNTIME)
	    @Target(METHOD)
		public @interface PodeConsultar { } 
		
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_USUARIOS_GRUPOS_PERMISSOES')")
		@Retention(RUNTIME)
	    @Target(METHOD)
		public @interface PodeEditar { }
		
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and "
					+ "(hasAuthority('EDITAR_USUARIOS_GRUPOS_PERMISSOES') or "
					+ "@algaSecurity.usuarioAutenticadoIgual(#id))")
		@Retention(RUNTIME)
	    @Target(METHOD)
		public @interface PodeAlterarUsuarioGrupoPermissoes { }
		
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and "
					+ "@algaSecurity.usuarioAutenticadoIgual(#id)")
		@Retention(RUNTIME)
	    @Target(METHOD)
		public @interface PodeAlterarPropriaSenha { }
		
	}
	
	
	public @interface Estatisticas {
		
		@PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('GERAR_RELATORIOS')")
		@Retention(RUNTIME)
	    @Target(METHOD)
		public @interface PodeConsultar { } 
		
	}
	
	
}
