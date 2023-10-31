package com.lojavirtual.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lojavirtual.api.modelDto.UsuarioDto;
import com.lojavirtual.domain.model.Usuario;

@Component
public class UsuarioDtoAssembler {
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	public UsuarioDto toDto(Usuario usuarioAdm) {
		
		return modelMapper.map(usuarioAdm, UsuarioDto.class);
	}
	
	public List<UsuarioDto> toDtoCollection(Collection<Usuario> usuarioAdms){
		return  usuarioAdms.stream()
				.map(usuarioAdm -> toDto(usuarioAdm))
				.collect(Collectors.toList());
	}
}
