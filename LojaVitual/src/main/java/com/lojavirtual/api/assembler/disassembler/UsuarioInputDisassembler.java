package com.lojavirtual.api.assembler.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lojavirtual.api.modelDto.input.UsuarioDtoInput;
import com.lojavirtual.domain.model.Usuario;

@Component
public class UsuarioInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Usuario toDomainObject(UsuarioDtoInput usuarioAdmDtoInput) {
		return modelMapper.map(usuarioAdmDtoInput, Usuario.class);
		
	}
	
	
	public void copyToDomainObject(UsuarioDtoInput usuarioAdmDtoInput,
			Usuario usuarioAdm) {
		
			modelMapper.map(usuarioAdmDtoInput, usuarioAdm);
	}
	
}
