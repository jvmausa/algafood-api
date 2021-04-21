package com.jvmausa.algafood.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;
import lombok.EqualsAndHashCode;

//@Table(name = "tab_cozinha")
@JsonRootName("cozinha")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cozinha {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	//@Column(name = "nom_cozinha")
	//@JsonIgnore remove da representação este atributo
	//@JsonProperty("titulo") altera o nome deste atributo na representação
	@Column(nullable = false)
	private String nome;

}
