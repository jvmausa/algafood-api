package com.jvmausa.algafood.api.v1.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoModel extends RepresentationModel<ItemPedidoModel>{

	@ApiModelProperty(example = "1")
    private Long produtoId;
	
	@ApiModelProperty(example = "Porco com molho agridoce")
    private String produtoNome;
	
	@ApiModelProperty(example = "1")
    private Integer quantidade;
	
	@ApiModelProperty(example = "79.90")
    private BigDecimal precoUnitario;
	
	@ApiModelProperty(example = "79.90")
    private BigDecimal precoTotal;
	
	@ApiModelProperty(example = "Ponto da carne bem passado")
    private String observacao;            
}  