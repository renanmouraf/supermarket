package com.supermarkt.supermercado;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemEstoqueDTO {
	
	private Long id;
	private String nome;
	private String descricao;
	private Integer quantidade;
	private BigDecimal preco;
	private BigDecimal precoPromocional;
	private Long supermercadoId;

}
