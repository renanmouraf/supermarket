package com.supermarkt.supermercado;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.supermarkt.infra.excecao.EntidadeNaoEncontradaException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SupermercadoAPI {
	
	private final SupermercadoServico supermercadoServico;

	@GetMapping("/admin/supermercados")
	public ResponseEntity<List<SupermercadoDTO>> lista() throws EntidadeNaoEncontradaException {
		return ResponseEntity.ok(supermercadoServico.lista());
	}
	
	@GetMapping("/admin/supermercados/{nome}")
	public ResponseEntity<List<SupermercadoDTO>> buscarPorNome(@PathVariable("nome") String nome) throws EntidadeNaoEncontradaException {
		return ResponseEntity.ok(supermercadoServico.buscarPorNome(nome));
	}
	
	@GetMapping("/supermercados/{id}")
	public ResponseEntity<SupermercadoDTO> detalha(@PathVariable("id") Long id) throws EntidadeNaoEncontradaException {
		return ResponseEntity.ok(supermercadoServico.detalha(id));
	}
	
	@GetMapping("/supermercados")
	public ResponseEntity<List<SupermercadoDTO>> detalhePorIds(@RequestParam List<Long> ids) throws EntidadeNaoEncontradaException {
		return ResponseEntity.ok(supermercadoServico.detalhePorIds(ids));
	}
	
	@DeleteMapping("/admin/supermercados/{id}")
	public ResponseEntity<?> remove(@PathVariable("id") Long id) {
		supermercadoServico.remove(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/parceiros/supermercados/{id}")
	public ResponseEntity<SupermercadoDTO> detalhaParceiro(@PathVariable("id") Long id) throws EntidadeNaoEncontradaException {
		return ResponseEntity.ok(supermercadoServico.detalhaParceiro(id));
	}

	@PostMapping("/admin/supermercados")
	public ResponseEntity<Supermercado> adiciona(@RequestBody Supermercado supermercado) {
		return ResponseEntity.status(HttpStatus.CREATED).body(supermercadoServico.adiciona(supermercado));
	}

	@PutMapping("/admin/supermercados/{id}")
	public ResponseEntity<Supermercado> atualiza(@RequestBody Supermercado supermercado) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(supermercadoServico.atualiza(supermercado));
	}
	
	@PutMapping("/supermercados/{id}/favoritar")
	public ResponseEntity<Supermercado> favoritar(@RequestBody Supermercado supermercado) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(supermercadoServico.atualiza(supermercado));
	}

}
