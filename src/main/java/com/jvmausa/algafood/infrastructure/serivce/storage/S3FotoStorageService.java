package com.jvmausa.algafood.infrastructure.serivce.storage;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.jvmausa.algafood.domain.service.FotoStorageService;

@Service
public class S3FotoStorageService implements FotoStorageService{

	
	@Autowired
	private AmazonS3 amazonS3;
	
	@Override
	public InputStream recuperar(String nomeArquivo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void armazenar(NovaFoto novaFoto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remover(String nomeArquivo) {
		// TODO Auto-generated method stub
		
	}

	
	
}
