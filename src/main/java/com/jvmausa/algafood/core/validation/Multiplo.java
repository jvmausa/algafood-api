 package com.jvmausa.algafood.core.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = {MultiploValidator.class})
public @interface Multiplo {
	
	String message() default "multiplo inválido";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
	int numero();

}

/*
 * classe de exemplo de criação de validador de dados customizado. Regra está no MultiploValidator.java
 * 
 * 
 */