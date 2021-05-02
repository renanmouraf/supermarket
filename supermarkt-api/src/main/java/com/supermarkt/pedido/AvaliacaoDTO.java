package com.supermarkt.pedido;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AvaliacaoDTO {

	private Long id;
	private Integer nota;
	private String comentario;
	private PedidoDTO pedido;

}
