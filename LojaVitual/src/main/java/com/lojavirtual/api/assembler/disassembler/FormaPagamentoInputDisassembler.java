package com.lojavirtual.api.assembler.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lojavirtual.api.modelDto.FormaPagamentoDto;
import com.lojavirtual.api.modelDto.input.FormaPagamentoDtoInput;
import com.lojavirtual.domain.model.FormaPagamento;
@Component
public class FormaPagamentoInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public FormaPagamento toDomainObject(FormaPagamentoDtoInput formaPagamentoDtoInput) {
		return modelMapper.map(formaPagamentoDtoInput, FormaPagamento.class);
		
	}
	
	
	public void copyToDomainObject(FormaPagamentoDtoInput formaPagamentoDtoInput,
			FormaPagamento formaPagamento) {
		
			modelMapper.map(formaPagamentoDtoInput, formaPagamento);
	}
}
