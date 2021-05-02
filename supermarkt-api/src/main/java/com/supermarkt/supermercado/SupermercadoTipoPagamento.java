package com.supermarkt.supermercado;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.supermarkt.admin.TipoPagamento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SupermercadoTipoPagamento {
	
	@EmbeddedId
	SupermercadoTipoPagamentoId id;
	
	@ManyToOne
	@MapsId("supermercadoId")
	private Supermercado supermercado;
	
	@ManyToOne
	@MapsId("tipoPagamentoId")
	private TipoPagamento tipoPagamento;
	
	@Embeddable
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class SupermercadoTipoPagamentoId implements Serializable {
		private static final long serialVersionUID = 1L;

		@Column(name = "supermercado_id")
		private Long supermercadoId;

		@Column(name = "tipo_pagamento_id")
		private Long tipoPagamentoId;
	}

}
