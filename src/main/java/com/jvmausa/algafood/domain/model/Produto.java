package com.jvmausa.algafood.domain.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Produto {
	
	private BigDecimal id;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	private boolean ativo;
	

}
