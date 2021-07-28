package com.jvmausa.algafood.core.validation;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;

import org.springframework.beans.BeanUtils;

public class ValorZeroIncluDescricaoValidator implements ConstraintValidator<ValorZeroIncluiDescricao, Object> {

	private String valorField;
	private String descricaoField;
	private String descricaoObrigatoria;

	@Override
	public void initialize(ValorZeroIncluiDescricao constraintAnnotation) {
		this.valorField = constraintAnnotation.valorField();
		this.descricaoField = constraintAnnotation.descricaoField();
		this.descricaoObrigatoria = constraintAnnotation.descricaoObrigatoria();
	}

	@Override
	public boolean isValid(Object ObjetoValidacao, ConstraintValidatorContext context) {
		boolean valido = true;

		try {
			BigDecimal valor = (BigDecimal) BeanUtils.getPropertyDescriptor(ObjetoValidacao.getClass(), valorField)
					.getReadMethod().invoke(ObjetoValidacao);

			String descricao = (String) BeanUtils.getPropertyDescriptor(ObjetoValidacao.getClass(), descricaoField)
					.getReadMethod().invoke(ObjetoValidacao);

			if (valor != null && BigDecimal.ZERO.compareTo(valor) == 0 && descricao != null) {
				valido = descricao.toLowerCase().contains(this.descricaoObrigatoria.toLowerCase());
			}

			return valido;

		} catch (Exception e) {
			throw new ValidationException(e);
		}

	}

}
