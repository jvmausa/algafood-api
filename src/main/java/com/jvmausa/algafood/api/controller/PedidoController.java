package com.jvmausa.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.jvmausa.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.jvmausa.algafood.domain.exception.NegocioException;
import com.jvmausa.algafood.domain.model.Pedido;
import com.jvmausa.algafood.domain.model.Usuario;
import com.jvmausa.algafood.domain.repository.PedidoRepository;
import com.jvmausa.algafood.domain.repository.filter.PedidoFilter;
import com.jvmausa.algafood.domain.service.EmissaoPedidoService;
import com.jvmausa.algafood.infrastructure.repository.spec.PedidoSpecs;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

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
    
    
    @GetMapping
    public List<PedidoResumoModel> pesquisar(PedidoFilter filter) {
        List<Pedido> todosPedidos = pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filter));
        
        return pedidoResumoModelAssembler.toCollectionModel(todosPedidos);
    }
    
    
    @GetMapping("/{codigoPedido}")
    public PedidoModel buscar(@PathVariable String codigoPedido) {
        Pedido pedido = emissaoPedido.buscarOuFalhar(codigoPedido);
        
        return pedidoModelAssembler.toModel(pedido);
    }            
    
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
}  