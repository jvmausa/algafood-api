package com.jvmausa.algafood.api.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jvmausa.algafood.api.assembler.PedidoInputDisassembler;
import com.jvmausa.algafood.api.assembler.PedidoModelAssembler;
import com.jvmausa.algafood.api.assembler.PedidoResumoModelAssembler;
import com.jvmausa.algafood.api.model.PedidoModel;
import com.jvmausa.algafood.api.model.PedidoResumoModel;
import com.jvmausa.algafood.api.model.input.PedidoInput;
import com.jvmausa.algafood.api.springfox.controller.PedidoControllerOpenApi;
import com.jvmausa.algafood.core.data.PageableTranslator;
import com.jvmausa.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.jvmausa.algafood.domain.exception.NegocioException;
import com.jvmausa.algafood.domain.filter.PedidoFilter;
import com.jvmausa.algafood.domain.model.Pedido;
import com.jvmausa.algafood.domain.model.Usuario;
import com.jvmausa.algafood.domain.repository.PedidoRepository;
import com.jvmausa.algafood.domain.service.EmissaoPedidoService;
import com.jvmausa.algafood.infrastructure.repository.spec.PedidoSpecs;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RestController
@RequestMapping(value = "/pedidos")
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
    
    @Override
	@ApiImplicitParams({
    	@ApiImplicitParam(value = "Nome das propriesdades para filtras na resposta separados por vírgula",
    						name = "campos", paramType = "query", type = "string")
    })
    @GetMapping
    public Page<PedidoResumoModel> pesquisar(PedidoFilter filtro, @PageableDefault(size = 2) Pageable pageable) {    	
    	pageable = traduzirPageable(pageable);
    	
        Page<Pedido> pedidosPage = pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filtro), pageable);
        
        List<PedidoResumoModel> pedidosResumoModel = pedidoResumoModelAssembler
                .toCollectionModel(pedidosPage.getContent());
        
        Page<PedidoResumoModel> pedidosResumoModelPage = new PageImpl<>(
                pedidosResumoModel, pageable, pedidosPage.getTotalElements());
        
        return pedidosResumoModelPage;
    }
    
    
    @Override
	@ApiImplicitParams({
    	@ApiImplicitParam(value = "Nome das propriesdades para filtras na resposta separados por vírgula",
    						name = "campos", paramType = "query", type = "string")
    })
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
				"restaurante.nome", "restaurante.nome",
				"nomeCliente", "cliente.nome",
				"valorTotal", "valorTotal"
			);
    	
    	
    	return PageableTranslator.translate(apiPageable, mapeamento); 

    }
    
}  