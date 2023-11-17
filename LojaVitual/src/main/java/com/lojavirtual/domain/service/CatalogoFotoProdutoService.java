package com.lojavirtual.domain.service;

import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lojavirtual.domain.model.FotoProduto;
import com.lojavirtual.domain.repository.ProdutoRepository;
import com.lojavirtual.domain.service.FotoStorageService.NovaFoto;

import jakarta.transaction.Transactional;

@Service
public class CatalogoFotoProdutoService {
	
	
	@Autowired
	private FotoStorageService fotoStorageService;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Transactional
	public FotoProduto salvar(FotoProduto fotoProduto, InputStream inputStream) {
		
		Long produtoId = fotoProduto.getProduto().getId();
		String nomeNovoArquivo = fotoStorageService.gerarNomeArquivo(fotoProduto.getNomeArquivo());
		String nomeArquivoExistente = null;
		
		Optional<FotoProduto> fotoExistente = produtoRepository.findFotoById(produtoId);
		
		
		if(fotoExistente.isPresent()) {
			nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
			produtoRepository.delete(fotoProduto);
			excluir(produtoId);
		}
		
		fotoProduto.setNomeArquivo(nomeNovoArquivo);
		fotoProduto = produtoRepository.save(fotoProduto);
		produtoRepository.flush();
		
		NovaFoto novaFoto = NovaFoto.builder()
				.nomeArquivo(fotoProduto.getNomeArquivo())
				.inputStream(inputStream)
				.build();
		
		fotoStorageService.substituir(nomeArquivoExistente, novaFoto);
		
		return fotoProduto;
	}
	
	@Transactional
	public void excluir(Long produtoId) {
		FotoProduto foto = buscarOuFalhar(produtoId);
		
		produtoRepository.delete(foto);
		produtoRepository.flush();
		
		fotoStorageService.remover(foto.getNomeArquivo());
	}
	
	
	public FotoProduto buscarOuFalhar(Long produtoId) {
		return produtoRepository.findFotoById(produtoId)
				.orElseThrow(() -> new RuntimeException("Foto de produto não encontrada"));
	}
	
}
