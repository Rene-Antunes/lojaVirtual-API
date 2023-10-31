package com.lojavirtual.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lojavirtual.api.modelDto.FormaPagamentoDto;
import com.lojavirtual.domain.model.FormaPagamento;

@Component
public class FormaPagamentoDtoAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public FormaPagamentoDto toDto(FormaPagamento formaPagamento) {
		
		return modelMapper.map(formaPagamento, FormaPagamentoDto.class);
	}
	
	
	public List<FormaPagamentoDto> toCollectionDto(List<FormaPagamento> formaPagamentos) {
			
			return formaPagamentos.stream()
					.map(pagamento -> toDto(pagamento))
					.collect(Collectors.toList());
		}
	
}
