package com.jvmausa.algafood.api.model.mixin;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jvmausa.algafood.domain.model.Restaurante;

public abstract class CozinhaMixin {

	
	// @JsonIgnore para ignorar na representação, caso contrário fica em looping
	@JsonIgnore
	private List<Restaurante> restaurantes = new ArrayList<>(); 
	
}
