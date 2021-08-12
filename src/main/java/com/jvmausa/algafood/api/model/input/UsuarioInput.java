package com.jvmausa.algafood.api.model.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioInput {

	@NotBlank
	String nome;
	
	@NotBlank
	@Email
	String email;
	
}
