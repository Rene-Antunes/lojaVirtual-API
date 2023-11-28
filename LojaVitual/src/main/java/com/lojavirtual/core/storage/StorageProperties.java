package com.lojavirtual.core.storage;

import java.nio.file.Path;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.amazonaws.regions.Regions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties("lojavirtual.storage")
public class StorageProperties {
	private Local local = new Local();
	private S3 s3 = new S3();
	private TipoStorage tipo = TipoStorage.LOCAL;
	
	
	public enum TipoStorage{
		LOCAL, S3
	}
	
	
	@Getter
	@Setter
	public class Local{
		private Path diretorioFotos;
	}
	
	@Getter
	@Setter
	public class S3{
		private String idChaveAcesso;
		private String chaveAcessoSecreta;
		private String bucket;
		private Regions regiao;
		private String diretorioFotos;
	}
	
	
	
}
