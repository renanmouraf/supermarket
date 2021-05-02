package com.supermarkt.supermercado;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.supermarkt.admin.TipoPagamento;
import com.supermarkt.infra.excecao.EntidadeNaoEncontradaException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SupermercadoTipoPagamentoAPI {
	
	private final SupermercadoTipoPagamentoServico supermercadoTipoPagamentoServico;
	
	@PostMapping("/parceiros/supermercados/{idSupermercado}/tipo-pagamento")
	public ResponseEntity<?> adiciona(@PathVariable("idSupermercado") Long idRestaurante, @RequestBody TipoPagamento tipoPagamento) {
		supermercadoTipoPagamentoServico.adiciona(idRestaurante, tipoPagamento);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/parceiros/supermercados/{idSupermercado}/tipo-pagamento/{idTipoPagamento}")
	public ResponseEntity<?> removeDoRestaurante(@PathVariable("idSupermercado") Long idRestaurante, @PathVariable("idTipoPagamento") Long idFormaDePagamento) {
		supermercadoTipoPagamentoServico.removeDoRestaurante(idRestaurante, idFormaDePagamento);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/supermercados/{idSupermercado}/tipo-pagamento")
	public ResponseEntity<List<TipoPagamento>> lista(@PathVariable("idSupermercado") Long idSupermercado) throws EntidadeNaoEncontradaException {
		return ResponseEntity.ok(supermercadoTipoPagamentoServico.lista(idSupermercado));
	}
	
}
