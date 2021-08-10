package com.jvmausa.algafood.api.model;

import com.jvmausa.algafood.domain.model.CidadeResumoModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoModel {

	private String cep;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private CidadeResumoModel cidade;

}
