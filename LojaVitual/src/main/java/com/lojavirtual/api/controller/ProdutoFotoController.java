package com.lojavirtual.api.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lojavirtual.api.assembler.FotoProdutoDtoAssembler;
import com.lojavirtual.api.modelDto.FotoProdutoDto;
import com.lojavirtual.api.modelDto.input.FotoProdutoDtoInput;
import com.lojavirtual.domain.exception.EntidadeNaoEncontradaException;
import com.lojavirtual.domain.model.FotoProduto;
import com.lojavirtual.domain.model.Produto;
import com.lojavirtual.domain.service.CadastroProdutoService;
import com.lojavirtual.domain.service.CatalogoFotoProdutoService;
import com.lojavirtual.domain.service.FotoStorageService;
import com.lojavirtual.domain.service.FotoStorageService.FotoRecuperada;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos/{produtoId}/foto")
public class ProdutoFotoController {
	
	@Autowired
	private CadastroProdutoService cadastroProdutoService;
	
	@Autowired
	private CatalogoFotoProdutoService catalogoFotoProdutoService;
	
	@Autowired
	private FotoProdutoDtoAssembler fotoProdutoDtoAssembler;
	
	@Autowired
	private FotoStorageService fotoStorageService;
	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public FotoProdutoDto buscar (@PathVariable Long produtoId) {
		
		FotoProduto fotoProduto = catalogoFotoProdutoService.buscarOuFalhar(produtoId);
		return fotoProdutoDtoAssembler.toDto(fotoProduto);
	}
	
	@GetMapping(produces = MediaType.ALL_VALUE)
	public ResponseEntity<?> servirFoto(@PathVariable Long produtoId,
			@RequestHeader(name = "accept") String acceptHeader) throws HttpMediaTypeNotAcceptableException{
		
		try {
			
			FotoProduto fotoProduto = catalogoFotoProdutoService.buscarOuFalhar(produtoId);
			MediaType mediaTypeFoto = MediaType.parseMediaType(fotoProduto.getContentType());
			List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(acceptHeader);
			verificarCompatibilidadeMediaType(mediaTypeFoto, mediaTypesAceitas);
		
			
			FotoRecuperada fotoRecuperada = fotoStorageService.recuperar(fotoProduto.getNomeArquivo());
			
			if (fotoRecuperada.temUrl()) {
				return ResponseEntity
						.status(HttpStatus.FOUND)
						.header(HttpHeaders.LOCATION, fotoRecuperada.getUrl())
						.build();
			}else {
				return ResponseEntity.ok()
						.contentType(mediaTypeFoto)
						.body(new InputStreamResource(fotoRecuperada.getInputStream()));
			}
		
		} catch (EntidadeNaoEncontradaException e) {
			
			return ResponseEntity.notFound().build();
		}
		
	}
	

	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoProdutoDto atualizarFoto(@PathVariable Long produtoId,
			@Valid FotoProdutoDtoInput fotoProdutoDtoInput,
			@RequestPart(required = true) MultipartFile arquivo) throws IOException {
		
		Produto produto = cadastroProdutoService.buscarOuFalhar(produtoId);
		
		FotoProduto foto = new FotoProduto();
		
		foto.setProduto(produto);
		foto.setDescricao(fotoProdutoDtoInput.getDescricao());
		foto.setContentType(arquivo.getContentType());
		foto.setTamanho(arquivo.getSize());
		foto.setNomeArquivo(arquivo.getOriginalFilename());
		
		FotoProduto fotoSalva = catalogoFotoProdutoService.salvar(foto, arquivo.getInputStream());
		
		return fotoProdutoDtoAssembler.toDto(fotoSalva);
	}
	
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long produtoId) {
		catalogoFotoProdutoService.excluir(produtoId);
	}
	
	
	private void verificarCompatibilidadeMediaType(MediaType mediaTypeFoto,
			List<MediaType> mediaTypesAceitas) throws HttpMediaTypeNotAcceptableException {

		boolean compativel = mediaTypesAceitas.stream()
				.anyMatch(mediaTypeAceita -> mediaTypeAceita.isCompatibleWith(mediaTypeFoto));
		
		if(!compativel) {
			throw new HttpMediaTypeNotAcceptableException(mediaTypesAceitas);
		}
		
	}
}
