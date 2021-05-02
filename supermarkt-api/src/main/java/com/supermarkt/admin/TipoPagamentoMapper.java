package com.supermarkt.admin;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TipoPagamentoMapper {
	
	TipoPagamento paraTipoPagamento(TipoPagamentoDTO tipoPagamentoDto);
	
	TipoPagamentoDTO paraTipoPagamentoDto(TipoPagamento tipoPagamento);
	
	List<TipoPagamentoDTO> paraTipoPagamentoDto(List<TipoPagamento> tipoPagamento);
	
	List<TipoPagamento> paraTipoPagamento(List<TipoPagamentoDTO> tipoPagamentoDto);

}
