package com.jvmausa.algafood.api.v1.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jvmausa.algafood.api.springfox.controller.PedidoControllerOpenApi;
import com.jvmausa.algafood.api.v1.assembler.PedidoInputDisassembler;
import com.jvmausa.algafood.api.v1.assembler.PedidoModelAssembler;
import com.jvmausa.algafood.api.v1.assembler.PedidoResumoModelAssembler;
import com.jvmausa.algafood.api.v1.model.PedidoModel;
import com.jvmausa.algafood.api.v1.model.PedidoResumoModel;
import com.jvmausa.algafood.api.v1.model.input.PedidoInput;
import com.jvmausa.algafood.core.data.PageWrapper;
import com.jvmausa.algafood.core.data.PageableTranslator;
import com.jvmausa.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.jvmausa.algafood.domain.exception.NegocioException;
import com.jvmausa.algafood.domain.filter.PedidoFilter;
import com.jvmausa.algafood.domain.model.Pedido;
import com.jvmausa.algafood.domain.model.Usuario;
import com.jvmausa.algafood.domain.repository.PedidoRepository;
import com.jvmausa.algafood.domain.service.EmissaoPedidoService;
import com.jvmausa.algafood.infrastructure.repository.spec.PedidoSpecs;

@RestController
@RequestMapping(value = "/v1/pedidos")
public class PedidoController implements PedidoControllerOpenApi {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private EmissaoPedidoService emissaoPedido;

	@Autowired
	private PedidoModelAssembler pedidoModelAssembler;

	@Autowired
	private PedidoResumoModelAssembler pedidoResumoModelAssembler;

	@Autowired
	private PedidoInputDisassembler pedidoInputDisassembler;

	@Autowired
	private PagedResourcesAssembler<Pedido> pagedResourcesAssembler;

	@Override
	@GetMapping
	public PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filtro, @PageableDefault(size = 10) Pageable pageable) {
		Pageable pageableTraduzido = traduzirPageable(pageable);

		// primeiro faz o findAll com os filtros e traduçoes
		Page<Pedido> pedidosPage = pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filtro), pageableTraduzido);

		pedidosPage = new PageWrapper<>(pedidosPage, pageable);
		
		//agora passa pro Model sem as traduções, com o original recebido no parâmetro
		return pagedResourcesAssembler.toModel(pedidosPage, pedidoResumoModelAssembler);
	}

	@Override
	@GetMapping("/{codigoPedido}")
	public PedidoModel buscar(@PathVariable String codigoPedido) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(codigoPedido);

		return pedidoModelAssembler.toModel(pedido);
	}

	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoModel adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
		try {
			Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

			// TODO pegar usuário autenticado
			novoPedido.setCliente(new Usuario());
			novoPedido.getCliente().setId(1L);

			novoPedido = emissaoPedido.emitir(novoPedido);

			return pedidoModelAssembler.toModel(novoPedido);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	private Pageable traduzirPageable(Pageable apiPageable) {
		var mapeamento = Map.of(
				"codigo", "codigo",
				"subtotal", "subtotal",
				"taxaFrete", "taxaFrete",
				"valorTotal", "valorTotal",
				"dataCriacao", "dataCriacao",
				"nomerestaurante", "restaurante.nome",
				"restaurante.id", "restaurante.id",
				"cliente.id", "cliente.id",
				"cliente.nome", "cliente.nome"
			);

		return PageableTranslator.translate(apiPageable, mapeamento);

	}

}