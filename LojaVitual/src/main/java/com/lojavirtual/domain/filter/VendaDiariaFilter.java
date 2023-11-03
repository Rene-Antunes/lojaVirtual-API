package com.lojavirtual.domain.filter;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class VendaDiariaFilter {
	
	private LocalDateTime dataCriacaoInicio;
	private LocalDateTime dataCriacaoFim;
}
