package com.supermarkt.pedido;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.supermarkt.supermercado.Supermercado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pedido {
	
	public static enum Situacao {
		REALIZADO,
		PAGO,
		CONFIRMADO,
		PRONTO,
		SAIU_PARA_ENTREGA,
		ENTREGUE;
	}

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private LocalDateTime dataHora;

	@NotNull @Enumerated(EnumType.STRING)
	private Situacao situacao;
	
	@ManyToOne(optional=false)
	private Supermercado supermercado;

	@OneToOne(cascade=CascadeType.PERSIST, optional=false, mappedBy="pedido")
	private Entrega entrega;

	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="pedido")
	private List<ItemDoPedido> itens;

}
