package com.supermarkt.pedido;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
class MediaAvaliacoesDTO {

	private Long supermercadoId;
	private Double media;

}