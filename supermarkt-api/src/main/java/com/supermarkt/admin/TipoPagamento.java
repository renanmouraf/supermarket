package com.supermarkt.admin;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TipoPagamento {
	
	public static enum Forma {
		CARTAO_CREDITO("Cartão de Crédito", "CARTAO_CREDITO"), 
		CARTAO_DEBITO("Cartão de Débito", "CARTAO_DEBITO"), 
		VALE_ALIMENTACAO("Vale Alimentação", "VALE_ALIMENTACAO");
		
		private String descricao;
		private String codigo;

		private Forma(String descricao, String codigo) {
			this.descricao = descricao;
			this.codigo = codigo;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		@Override
		public String toString() {
			return descricao;
		}

		public String getCodigo() {
			return codigo;
		}

		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}
		
		public String completo() {
			return "{name:" + this.codigo +",{code:"+ this.codigo+"}";
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Forma forma;

	@NotBlank
	@Size(max = 100)
	private String nome;

}
