package com.supermarkt.pedido;

import com.supermarkt.supermercado.SupermercadoDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SupermercadoComAvaliacaoDTO {
	
	SupermercadoDTO supermercado;
	Double mediaDasAvaliacoes;

}
