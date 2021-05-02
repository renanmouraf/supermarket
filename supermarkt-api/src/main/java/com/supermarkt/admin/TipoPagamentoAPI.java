package com.supermarkt.admin;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.supermarkt.infra.excecao.EntidadeNaoEncontradaException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class TipoPagamentoAPI {

	private final TipoPagamentoServico tipoPagamentoServico;

	@GetMapping("/tipo-pagamento")
	public ResponseEntity<List<TipoPagamentoDTO>> lista() throws EntidadeNaoEncontradaException {
		return ResponseEntity.ok(tipoPagamentoServico.listaTodos());
	}

	@GetMapping("/admin/tipo-pagamento/formas")
	public ResponseEntity<List<FormaPagamentoDTO>> formas() throws EntidadeNaoEncontradaException {
		return ResponseEntity.ok(tipoPagamentoServico.formas());
	}

	@PostMapping("/admin/tipo-pagamento")
	public ResponseEntity<TipoPagamentoDTO> adiciona(@RequestBody TipoPagamentoDTO tipoPagamento) {
		return ResponseEntity.status(HttpStatus.CREATED).body(tipoPagamentoServico.adiciona(tipoPagamento));
	}

	@PutMapping("/admin/tipo-pagamento/{id}")
	public ResponseEntity<TipoPagamentoDTO> atualiza(@RequestBody TipoPagamentoDTO tipoPagamento) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(tipoPagamentoServico.atualiza(tipoPagamento));
	}

	@DeleteMapping("/admin/tipo-pagamento/{id}")
	public ResponseEntity<?> remove(@PathVariable("id") Long id) {
		tipoPagamentoServico.remove(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/admin/tipo-pagamento/{id}")
	public ResponseEntity<TipoPagamentoDTO> tipoPorId(@PathVariable("id") Long id) throws EntidadeNaoEncontradaException {
		return ResponseEntity.ok(tipoPagamentoServico.tipoPorId(id));
	}
	
	@GetMapping("/tipo-pagamento/{nome}")
	public ResponseEntity<List<TipoPagamentoDTO>> buscarPorNome(@PathVariable("nome") String nome) throws EntidadeNaoEncontradaException {
		return ResponseEntity.ok(tipoPagamentoServico.buscarPorNome(nome));
	}
}
