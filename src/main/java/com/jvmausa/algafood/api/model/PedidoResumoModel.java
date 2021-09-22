package com.jvmausa.algafood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "pedidos")
@Setter
@Getter
public class PedidoResumoModel extends RepresentationModel<PedidoResumoModel>{

	@ApiModelProperty(example = "023caf9a-cb96-4010-bb3d-8409cb13f228")
    private String codigo;
	
	@ApiModelProperty(example = "65.90")
    private BigDecimal subtotal;
	
	@ApiModelProperty(example = "10.90")
    private BigDecimal taxaFrete;
	
	@ApiModelProperty(example = "122.99")
    private BigDecimal valorTotal;
	
	@ApiModelProperty(example = "CONFIRMADO")
    private String status;
	
    private OffsetDateTime dataCriacao;
    
    private RestauranteResumoModel restaurante;
    private UsuarioModel cliente;
} 