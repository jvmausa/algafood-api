package com.jvmausa.algafood.api.springfox.model;

import com.jvmausa.algafood.api.model.CozinhaModel;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@ApiModel("CozinhasModel")
@Getter
@Setter
public class CozinhasModelOpenApi extends PageModelOpenApi<CozinhaModel> {

}
