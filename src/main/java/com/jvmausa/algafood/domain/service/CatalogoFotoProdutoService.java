package com.jvmausa.algafood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jvmausa.algafood.domain.model.FotoProduto;
import com.jvmausa.algafood.domain.repository.ProdutoRepository;

@Service
public class CatalogoFotoProdutoService {

	@Autowired
	private ProdutoRepository ProdutoRepository;

	@Transactional
	public FotoProduto salvar(FotoProduto foto) {
		return ProdutoRepository.save(foto);

	}

}
