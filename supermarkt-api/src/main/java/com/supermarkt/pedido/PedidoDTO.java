package com.supermarkt.pedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.supermarkt.supermercado.Supermercado;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PedidoDTO {

	private Long id;
	private LocalDateTime dataHora;
	private Pedido.Situacao situacao;
	private Supermercado supermercado;
	private EntregaDTO entrega;
	private List<ItemDoPedidoDTO> itens;
	
	public BigDecimal getTotal() {
		BigDecimal total = supermercado.getTaxaDeEntregaEmReais() != null ? supermercado.getTaxaDeEntregaEmReais() : BigDecimal.ZERO;
		for (ItemDoPedidoDTO item : itens) {
			BigDecimal preco = item.getItemEstoque().getPrecoPromocional() != null ? item.getItemEstoque().getPrecoPromocional() : item.getItemEstoque().getPreco() ;
			total = total.add(preco.multiply(new BigDecimal(item.getQuantidade())));
		}
		return total;
	}
	
}
