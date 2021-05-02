package com.supermarkt.pedido;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EntregaDTO {

	private Long id;
	private Cliente cliente;
	private String cep;
	private String endereco;
	private String complemento;

}
