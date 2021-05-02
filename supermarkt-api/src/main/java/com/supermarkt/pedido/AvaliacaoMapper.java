package com.supermarkt.pedido;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AvaliacaoMapper {
	
	Avaliacao paraAvaliacao(AvaliacaoDTO avaliacaoDto);
	
	AvaliacaoDTO paraAvaliacaoDto(Avaliacao avaliacao);
	
	List<AvaliacaoDTO> paraAvaliacaoDto(List<Avaliacao> avaliacao);
	
	List<Avaliacao> paraAvaliacao(List<AvaliacaoDTO> avaliacaoDto);

}
