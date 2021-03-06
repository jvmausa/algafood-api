package com.jvmausa.algafood.api.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jvmausa.algafood.api.springfox.controller.v1.EstatisticasControllerOpenApi;
import com.jvmausa.algafood.api.v1.AlgaLinks;
import com.jvmausa.algafood.core.security.CheckSecurity;
import com.jvmausa.algafood.domain.filter.VendaDiariaFilter;
import com.jvmausa.algafood.domain.model.dto.VendaDiaria;
import com.jvmausa.algafood.domain.service.VendaQueryService;
import com.jvmausa.algafood.domain.service.VendaReportService;

@RestController
@RequestMapping(path = "/v1/estatisticas")
public class EstatisticasController implements EstatisticasControllerOpenApi {

	@Autowired
	private VendaReportService vendaReportService;

	@Autowired
	private VendaQueryService vendaQueryService;

	@Autowired
	private AlgaLinks algaLinks;

	
	@CheckSecurity.Estatisticas.PodeConsultar
	@Override
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public EstatisticasModel estatisticas() {

		EstatisticasModel estatisticasModel = new EstatisticasModel();
		estatisticasModel.add(algaLinks.linkToEstatisticasVendasDiarias("vendas-diarias"));

		return estatisticasModel;

	}

	public static class EstatisticasModel extends RepresentationModel<EstatisticasModel> {

	}

	@CheckSecurity.Estatisticas.PodeConsultar
	@Override
	@GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro,
			@RequestParam(required = false, defaultValue = "+00:00") String timeOffset) {
		return vendaQueryService.consultarVendasDiarias(filtro, timeOffset);

	}

	@CheckSecurity.Estatisticas.PodeConsultar
	@Override
	@GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter filtro,
			@RequestParam(required = false, defaultValue = "+00:00") String timeOffset) {

		var headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vendas-diarias.pdf");

		byte[] bytesPdf = vendaReportService.emitirVendasDiarias(filtro, timeOffset);

		return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).headers(headers).body(bytesPdf);

	}

}
