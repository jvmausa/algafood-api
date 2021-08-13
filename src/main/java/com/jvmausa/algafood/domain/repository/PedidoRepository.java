package com.jvmausa.algafood.domain.repository;

import org.springframework.stereotype.Repository;

import com.jvmausa.algafood.domain.model.Pedido;

@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Long> {

}   