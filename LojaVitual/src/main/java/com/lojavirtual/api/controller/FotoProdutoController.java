package com.lojavirtual.api.controller;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;
@RestController
@RequestMapping("/produtos/{produtoId}/foto")
public class FotoProdutoController {

	
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void atualizarFoto(@PathVariable Long produtoId,
			@RequestParam MultipartFile arquivo) {
		
		var nomeArquivo = UUID.randomUUID().toString()
				+ "_" + arquivo.getOriginalFilename();
		
		
		var fotoArquivo = Path.of("C:\\Users\\renea\\OneDrive\\Imagens\\catalogo",nomeArquivo);
			
		try {
			
			arquivo.transferTo(fotoArquivo);
			
		} catch (Exception e) {
			
			throw new RuntimeException();
		}
		
		
	}
	
	
	
	
	
}
