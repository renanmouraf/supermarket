package com.supermarkt.pedido;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

	Pedido paraPedido(PedidoDTO PedidoDto);
	
	PedidoDTO paraPedidoDto(Pedido pedido);
	
	List<PedidoDTO> paraPedidoDto(List<Pedido> pedido);
	
	List<Pedido> paraPedido(List<PedidoDTO> pedidoDto);
	
}
