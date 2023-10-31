package com.lojavirtual.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lojavirtual.domain.exception.NegocioException;
import com.lojavirtual.domain.exception.UsuarioNaoEncontradoException;
import com.lojavirtual.domain.model.Grupo;
import com.lojavirtual.domain.model.Usuario;
import com.lojavirtual.domain.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class CadastroUsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private CadastroGrupoService grupoService;
	
	@Transactional
	public Usuario salvar(Usuario usuarioAdm) {
		
		repository.detach(usuarioAdm);
		
		Optional<Usuario> usuarioExistente = repository.findByEmail(usuarioAdm.getEmail());
		
		if(usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuarioAdm)) {
			throw new NegocioException(String.format("Já existe um usuário cadastrado com o e-mail %s", usuarioAdm.getEmail()));
		}
		
			return repository.save(usuarioAdm);
	}
	
	@Transactional
	public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
		Usuario usuario = buscarOuFalhar(usuarioId);
		
		if (usuario.senhaNaoCoincideCom(senhaAtual)) {
			throw new NegocioException("Senha Atual informada não coincide com a senha do usuário");
		}
		
		usuario.setSenha(novaSenha);
	}
	
	
	@Transactional
	public void excluir (Long usuarioId) {
		try {
		 	buscarOuFalhar(usuarioId);
			repository.deleteById(usuarioId);
		} catch (UsuarioNaoEncontradoException e) {
			throw new UsuarioNaoEncontradoException("Usuario não foi encontrado, portanto não pode ser deletado ");
		}
		
	}
	
	public Usuario buscarOuFalhar(Long UsuarioAdmId) {
		return repository.findById(UsuarioAdmId)
				.orElseThrow(() -> new UsuarioNaoEncontradoException(UsuarioAdmId));
		
	}
	
	
	@Transactional
	public void associarGrupo(Long usuarioId, Long grupoId) {
		Usuario usuario = buscarOuFalhar(usuarioId);
		Grupo grupo = grupoService.buscarOuFalhar(grupoId);
		
		usuario.adicionarGrupo(grupo);
	}
	
	@Transactional
	public void desassociarGrupo(Long usuarioId, Long grupoId) {
		Usuario usuario = buscarOuFalhar(usuarioId);
		Grupo grupo = grupoService.buscarOuFalhar(grupoId);
		
		usuario.removerGrupo(grupo);
	}
	
}
