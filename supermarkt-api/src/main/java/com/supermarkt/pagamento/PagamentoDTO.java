package com.supermarkt.pagamento;

import java.math.BigDecimal;

import com.supermarkt.admin.TipoPagamento;
import com.supermarkt.pedido.PedidoDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PagamentoDTO {

	private Long id;
	private BigDecimal valor;
	private String nome;
	private String numero;
	private String expiracao;
	private String codigo;
	private Pagamento.Situacao situacao;
	private TipoPagamento tipoPagamento;
	private PedidoDTO pedido;

}
