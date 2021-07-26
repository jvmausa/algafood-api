package com.jvmausa.algafood.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.jvmausa.algafood.core.validation.Groups;

import lombok.Data;
import lombok.EqualsAndHashCode;

//@Table(name = "tab_cozinha")
@JsonRootName("cozinha")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cozinha {

	@NotNull(groups = Groups.CozinhaId.class)
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//@JsonIgnore remove da representação este atributo
	//@JsonProperty("titulo") altera o nome deste atributo na representação
	
	@NotBlank
	@Column(nullable = false)
	private String nome;
	
	// @JsonIgnore para ignorar na representação, caso contrário fica em looping
	@JsonIgnore
	@OneToMany(mappedBy = "cozinha")
	private List<Restaurante> restaurantes = new ArrayList<>(); 
	//cozinha tem muitos restaurantes. "Many" sempre significa que a propriedade é uma coleção
}
