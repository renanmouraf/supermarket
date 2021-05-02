package com.supermarkt.pagamento;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.supermarkt.infra.excecao.EntidadeNaoEncontradaException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pagamentos")
public class PagamentoAPI {
	
	private final PagamentoServico pagamentoServico;
	
	@GetMapping("/{id}")
	public ResponseEntity<PagamentoDTO> detalha(@PathVariable Long id) throws EntidadeNaoEncontradaException {
		return ResponseEntity.ok(pagamentoServico.detalha(id));
	}

	@PostMapping
	public ResponseEntity<PagamentoDTO> cria(@RequestBody Pagamento pagamento, UriComponentsBuilder uriBuilder) {
		PagamentoDTO pagamentoDto = pagamentoServico.cria(pagamento);
		URI path = uriBuilder.path("/pagamentos/{id}").buildAndExpand(pagamentoDto.getId()).toUri();
		return ResponseEntity.created(path).body(pagamentoDto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PagamentoDTO> confirma(@PathVariable Long id) throws EntidadeNaoEncontradaException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(pagamentoServico.confirma(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<PagamentoDTO> cancela(@PathVariable Long id) throws EntidadeNaoEncontradaException {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(pagamentoServico.cancela(id));
	}

}
