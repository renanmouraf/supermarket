package com.supermarkt.pedido;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.supermarkt.infra.excecao.EntidadeNaoEncontradaException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
class PedidoAPI {

	private final PedidoServico pedidoServico;

	@GetMapping("/pedidos")
	public ResponseEntity<List<PedidoDTO>> lista() throws EntidadeNaoEncontradaException {
		return ResponseEntity.ok(pedidoServico.lista());
	}

	@GetMapping("/pedidos/{id}")
	public ResponseEntity<PedidoDTO> porId(@PathVariable("id") Long id) throws EntidadeNaoEncontradaException {
		return ResponseEntity.ok(pedidoServico.porId(id));
	}

	@PostMapping("/pedidos")
	public ResponseEntity<PedidoDTO> adiciona(@RequestBody PedidoDTO pedido) {
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoServico.adiciona(pedido));
	}

	@PutMapping("/pedidos/{id}/situacao")
	public ResponseEntity<PedidoDTO> atualizaStatus(@RequestBody PedidoDTO pedido) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(pedidoServico.atualizaStatus(pedido));
	}

	@GetMapping("/parceiros/supermercados/{supermercadoId}/pedidos/pendentes")
	public ResponseEntity<List<PedidoDTO>> pendentes(@PathVariable("supermercadoId") Long supermercadoId) throws EntidadeNaoEncontradaException {
		return ResponseEntity.ok(pedidoServico.pendentes(supermercadoId));
	}
	
	@GetMapping("/pedidos/supermercados-avaliados")
	public ResponseEntity<List<SupermercadoComAvaliacaoDTO>> listaSupermercadosAvaliados() throws EntidadeNaoEncontradaException {
		return ResponseEntity.ok(pedidoServico.listaSupermercadosAvaliados());
	}
	
	@GetMapping("/pedidos/supermercado-avaliado/{supermercadoId}")
	public ResponseEntity<SupermercadoComAvaliacaoDTO> supermercadosAvaliados(@PathVariable("supermercadoId") Long supermercadoId) throws EntidadeNaoEncontradaException {
		return ResponseEntity.ok(pedidoServico.supermercadosAvaliados(supermercadoId));
	}

}
