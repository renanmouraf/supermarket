package com.supermarkt.pedido;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemDoPedidoMapper {
	
	ItemDoPedido paraItemDoPedido(ItemDoPedidoDTO itemDoPedidoDto);
	
	ItemDoPedidoDTO paraItemDoPedidoDto(ItemDoPedido itemDoPedido);
	
	List<ItemDoPedidoDTO> paraItemDoPedidoDto(List<ItemDoPedido> itensDoPedido);
	
	List<ItemDoPedido> paraItensDoPedido(List<ItemDoPedidoDTO> itensDoPedidoDto);
	
}
