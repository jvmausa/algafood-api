package com.jvmausa.algafood.infrastructure.repository.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.jvmausa.algafood.domain.filter.PedidoFilter;
import com.jvmausa.algafood.domain.model.Pedido;

public class PedidoSpecs {

	/*
	 * classe usada para consulta dinâmica
	 * ex: pedido com letra 'u' e taxa frete entre 10 e 20...
	 */

	public static Specification<Pedido> usandoFiltro(PedidoFilter filtro) {
		return (root, query, builder) -> {

			/*
			 * adicinado if() para evitar que fosso feito fetch com o count do SQL ao
			 * adicionar pageble
			 * 
			 */
			if (Pedido.class.equals(query.getResultType())) {
				root.fetch("restaurante").fetch("cozinha");
				root.fetch("cliente");
			}

			var predicates = new ArrayList<Predicate>();

			if (filtro.getClienteId() != null) {
				predicates.add(builder.equal(root.get("cliente"), filtro.getClienteId()));
			}

			if (filtro.getRestauranteId() != null) {
				predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
			}

			if (filtro.getDataCriacaoInicio() != null) {
				predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
			}

			if (filtro.getDataCriacaoFim() != null) {
				predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));
			}

			return builder.and(predicates.toArray(new Predicate[0]));
		};

	}

}
