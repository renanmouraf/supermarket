package com.supermarkt.pedido;

import com.supermarkt.supermercado.ItemEstoqueDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemDoPedidoDTO {

	private Long id;
	private Integer quantidade;
	private String observacao;
	private ItemEstoqueDTO itemEstoque;

}
