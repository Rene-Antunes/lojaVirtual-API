package com.lojavirtual.domain.service;

import java.util.List;

import com.lojavirtual.domain.filter.VendaDiariaFilter;
import com.lojavirtual.domain.model.estatisticas.VendaDiaria;

public interface VendaQueryService {
	
	List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro);
}
