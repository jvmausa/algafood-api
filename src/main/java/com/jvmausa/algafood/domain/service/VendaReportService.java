package com.jvmausa.algafood.domain.service;

import com.jvmausa.algafood.domain.filter.VendaDiariaFilter;

public interface VendaReportService {

	
	byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
	
}
