package com.jvmausa.algafood.infrastructure.serivce.storage;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

import com.jvmausa.algafood.core.storage.StorageProperties;
import com.jvmausa.algafood.domain.service.FotoStorageService;

//@Service
public class LocalFotoStorageService implements FotoStorageService {

	@Autowired
	private StorageProperties storageProperties;


	@Override
	public void armazenar(NovaFoto novaFoto) {

		Path arquivoPath = getArquivoPath(novaFoto.getNomeArquivo());

		try {
			FileCopyUtils.copy(novaFoto.getInputStream(), Files.newOutputStream(arquivoPath));
		} catch (Exception e) {
			throw new StorageException("Não foi possível armazenar aquivo.", e);
		}
	}

	

	@Override
	public void remover(String nomeArquivo) {
		Path arquivoPath = getArquivoPath(nomeArquivo);
		
		try {
			Files.deleteIfExists(arquivoPath);
		} catch (Exception e) {
			throw new StorageException("Não foi possível excluir aquivo.", e);
		}
		
	}
	
	private Path getArquivoPath(String nomeArquivo) {
		return storageProperties.getLocal().getDiretorioFotos()
				.resolve(Path.of(nomeArquivo));

	}



	@Override
	public InputStream recuperar(String nomeArquivo) {
				
		try {
			Path arquivoPath = getArquivoPath(nomeArquivo);
			
			return Files.newInputStream(arquivoPath);
		} catch (Exception e) {
			throw new StorageException("Não foi possível recuperar arquivo.", e);
		}
		

	}
	

}
