package com.jvmausa.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jvmausa.algafood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, 
										RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {
	
	public List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	
	
	@Query("from Restaurante r join fetch r.cozinha left join fetch r.formasPagamento") //resolve o problema de eager loading
	List<Restaurante> findAll();

	
	
	@Query("from Restaurante where nome like %:nome%") //existe como fazer isso via doc orm.xml: aula 5.10
	public Restaurante consultarPorNome(String nome);
	
	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);
	
	List<Restaurante> findTop2ByNomeContaining(String nome); // procura pelo top2 contendo
	
	int countByCozinhaId(Long cozinha); //conta quantos restaurantes existem com tal cozinha
	

}
