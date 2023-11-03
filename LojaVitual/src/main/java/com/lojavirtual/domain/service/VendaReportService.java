package com.lojavirtual.domain.service;

import com.lojavirtual.domain.filter.VendaDiariaFilter;

public interface VendaReportService {
	
	byte[] emitirVendasDiarias(VendaDiariaFilter filtro);
	
	
}
