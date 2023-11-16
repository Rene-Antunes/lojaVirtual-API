package com.lojavirtual.api.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lojavirtual.api.assembler.FotoProdutoDtoAssembler;
import com.lojavirtual.api.modelDto.FotoProdutoDto;
import com.lojavirtual.api.modelDto.input.FotoProdutoDtoInput;
import com.lojavirtual.domain.model.FotoProduto;
import com.lojavirtual.domain.model.Produto;
import com.lojavirtual.domain.service.CadastroProdutoService;
import com.lojavirtual.domain.service.CatalogoFotoProdutoService;

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
	
	
	
	@GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.IMAGE_PNG_VALUE)
	public FotoProdutoDto buscar (@PathVariable Long produtoId) {
		
		FotoProduto fotoProduto = catalogoFotoProdutoService.buscarOuFalhar(produtoId);
		return fotoProdutoDtoAssembler.toDto(fotoProduto);
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
	
}
