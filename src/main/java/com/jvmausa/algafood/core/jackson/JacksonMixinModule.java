package com.jvmausa.algafood.core.jackson;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.jvmausa.algafood.api.model.mixin.RestauranteMixin;
import com.jvmausa.algafood.domain.model.Restaurante;


@Component
public class JacksonMixinModule extends SimpleModule {

	private static final long serialVersionUID = 1L;

	public JacksonMixinModule() {
		setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
	}
	
}
