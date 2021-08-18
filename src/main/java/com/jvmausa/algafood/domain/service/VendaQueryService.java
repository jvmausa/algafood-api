package com.jvmausa.algafood.domain.service;

import java.util.List;

import com.jvmausa.algafood.domain.filter.VendaDiariaFilter;
import com.jvmausa.algafood.domain.model.dto.VendaDiaria;

public interface VendaQueryService {

	List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro);
	
	
}
