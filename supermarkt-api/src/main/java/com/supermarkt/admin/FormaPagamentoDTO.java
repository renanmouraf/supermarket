package com.supermarkt.admin;

import com.supermarkt.admin.TipoPagamento.Forma;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FormaPagamentoDTO {
	
	private String codigo;
	private String descricao;
	
	public FormaPagamentoDTO(Forma forma) {
		this(forma.getCodigo(), forma.getDescricao());
	}
}
