package com.supermarkt.supermercado;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SupermercadoDTO {

	private Long id;

	private String cnpj;

	private String nome;

	private String descricao;

	private String cep;

	private String endereco;

	private BigDecimal taxaDeEntregaEmReais;

	private Integer tempoDeEntregaMinimoEmMinutos;

	private Integer tempoDeEntregaMaximoEmMinutos;

	private Boolean favorito;
	
}
