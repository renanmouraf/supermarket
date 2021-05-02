package com.supermarkt.pagamento;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PagamentoMapper {
	
	Pagamento paraPagamento(PagamentoDTO pagamentoDto);
	
	PagamentoDTO paraPagamentoDto(Pagamento pagamento);
	
	List<PagamentoDTO> paraPagamentoDto(List<Pagamento> pagamento);
	
	List<Pagamento> paraPedido(List<PagamentoDTO> pagamentoDto);

}
