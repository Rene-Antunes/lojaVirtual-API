package com.lojavirtual.api.assembler.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lojavirtual.api.modelDto.input.GrupoDtoInput;
import com.lojavirtual.api.modelDto.input.UsuarioDtoInput;
import com.lojavirtual.domain.model.Grupo;
import com.lojavirtual.domain.model.Usuario;

@Component
public class GrupoInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Grupo toDomainObject(GrupoDtoInput grupoDtoInput) {
		return modelMapper.map(grupoDtoInput, Grupo.class);
		
	}
	
	
	public void copyToDomainObject(GrupoDtoInput grupoDtoInput,
			Grupo grupo) {
			modelMapper.map(grupoDtoInput, grupo);
	}
	
}
