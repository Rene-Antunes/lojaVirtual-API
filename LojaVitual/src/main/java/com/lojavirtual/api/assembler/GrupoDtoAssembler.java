package com.lojavirtual.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lojavirtual.api.modelDto.GrupoDto;
import com.lojavirtual.domain.model.Grupo;

@Component
public class GrupoDtoAssembler {
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	public GrupoDto toDto(Grupo grupo) {
		
		return modelMapper.map(grupo, GrupoDto.class);
	}
	
	public List<GrupoDto> toDtoCollection(Collection<Grupo> grupos){
		return  grupos.stream()
				.map(grupo -> toDto(grupo))
				.collect(Collectors.toList());
	}
}
