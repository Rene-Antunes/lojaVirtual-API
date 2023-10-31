package com.lojavirtual.api.assembler.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lojavirtual.api.modelDto.PermissaoDto;
import com.lojavirtual.api.modelDto.input.PermissaoDtoInput;
import com.lojavirtual.domain.model.Permissao;
@Component
public class PermissaoInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Permissao toDomainObject(PermissaoDtoInput permissaoDtoInput) {
		return modelMapper.map(permissaoDtoInput, Permissao.class);
		
	}
	
	
	public void copyToDomainObject(PermissaoDtoInput permissaoDtoInput,
			Permissao permissao) {
		
			modelMapper.map(permissaoDtoInput, permissao);
	}
	
}
