package com.supermarkt.supermercado;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemEstoqueMapper {
	
	@Mapping(source = "supermercadoId", target = "supermercado.id") 
	ItemEstoque paraItemEstoque(ItemEstoqueDTO itemEstoqueDto);
	
	@Mapping(source = "supermercado.id", target = "supermercadoId") 
	ItemEstoqueDTO paraItemEstoqueDto(ItemEstoque itemEstoque);
	
	List<ItemEstoqueDTO> paraItemEstoqueDto(List<ItemEstoque> itensEstoque);
	
	List<ItemEstoque> paraItemEstoque(List<ItemEstoqueDTO> itensEstoqueDto);

}
