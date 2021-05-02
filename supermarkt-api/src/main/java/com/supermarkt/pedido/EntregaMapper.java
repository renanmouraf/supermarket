package com.supermarkt.pedido;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntregaMapper {
	
	Entrega paraEntrega(EntregaDTO entregaDto);
	
	EntregaDTO paraEntregaDto(Entrega entrega);
	
	List<EntregaDTO> paraEntregaDto(List<Entrega> entrega);
	
	List<Entrega> paraEntrega(List<EntregaDTO> entregaDto);

}
