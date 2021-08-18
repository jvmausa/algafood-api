package com.jvmausa.algafood.infrastructure.serivce;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jvmausa.algafood.domain.filter.VendaDiariaFilter;
import com.jvmausa.algafood.domain.model.Pedido;
import com.jvmausa.algafood.domain.model.dto.VendaDiaria;
import com.jvmausa.algafood.domain.service.VendaQueryService;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService {

	@Autowired
	private EntityManager manager;

	@Override
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro) {
		var builder = manager.getCriteriaBuilder();
		var query = builder.createQuery(VendaDiaria.class);
		var root = query.from(Pedido.class);

		/*
		 * var functionDateData... faz a função data() do SQL, mas usando Criteria JPA
		 * 
		 * */
		
		var functionDateDataCriacao = builder.function("date", Date.class, root.get("dataCriacao"));

		var selection = builder.construct(VendaDiaria.class,
				functionDateDataCriacao, 
				builder.count(root.get("id")),
				builder.sum(root.get("valorTotal"))
				);
		
		query.select(selection);
		query.groupBy(functionDateDataCriacao);
		
		return manager.createQuery(query).getResultList();
		
	}
	
	
	
}
