package com.jvmausa.algafood.api.openapi.model;

import com.jvmausa.algafood.api.model.PedidoModel;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@ApiModel("PedidosResumoModel")
@Getter
@Setter
public class PedidosResumoModelOpenApi extends PageModelOpenApi<PedidoModel> {

}
