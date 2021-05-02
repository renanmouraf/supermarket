package com.supermarkt.supermercado;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupermercadoMapper {

	Supermercado paraSupermercado(SupermercadoDTO supermercadoDto);
	
	SupermercadoDTO paraSupermercadoDto(Supermercado supermercado);
	
	List<SupermercadoDTO> paraSupermercadoDto(List<Supermercado> supermercado);
	
	List<Supermercado> paraSupermercado(List<SupermercadoDTO> supermercadoDto);
}
