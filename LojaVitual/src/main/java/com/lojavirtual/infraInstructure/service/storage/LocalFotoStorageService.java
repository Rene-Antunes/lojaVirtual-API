package com.lojavirtual.infraInstructure.service.storage;

import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.lojavirtual.core.storage.StorageProperties;
import com.lojavirtual.domain.service.FotoStorageService;

@Service
public class LocalFotoStorageService implements FotoStorageService {
	
	@Autowired
	private StorageProperties storageProperties;

	@Override
	public FotoRecuperada recuperar(String nomeArquivo){
		
		try {
			Path arquivoPath = getArquivoPath(nomeArquivo);
			
			
			FotoRecuperada fotoRecuperada = FotoRecuperada.builder()
					.inputStream(Files.newInputStream(arquivoPath))
					.build();
			
			return fotoRecuperada;
			
		} catch (Exception e) {
			throw new StorageException("não foi possivel recuparar arquivo", e);
		}
		
	}
	
	@Override
	public void armazenar(NovaFoto novaFoto) {
				
		try {
			Path arquivoPath = getArquivoPath(novaFoto.getNomeArquivo());
			FileCopyUtils.copy(novaFoto.getInputStream(),
					Files.newOutputStream(arquivoPath));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void remover(String nomeArquivo) {
		Path arquivoPath = getArquivoPath(nomeArquivo);
		try {
			Files.deleteIfExists(arquivoPath);
			
		} catch (Exception e) {
			throw new StorageException("Não foi poível excluir arquivo", e);
		}
	}

	
	private Path getArquivoPath(String nomeArquivo) {
		return storageProperties.getLocal().getDiretorioFotos()
				.resolve(Path.of(nomeArquivo));
	}
	
}
