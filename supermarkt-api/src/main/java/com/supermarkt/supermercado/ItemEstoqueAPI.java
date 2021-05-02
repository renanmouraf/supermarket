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
import org.springframework.web.bind.annotation.RestController;

import com.supermarkt.infra.excecao.EntidadeNaoEncontradaException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ItemEstoqueAPI {
	
	private final ItemEstoqueServico itemEstoqueServico;
	
	@GetMapping("/supermercados/{idSupermercado}/estoque")
	public ResponseEntity<List<ItemEstoqueDTO>> estoqueDoSupermercado(@PathVariable("idSupermercado") Long idSupermercado) throws EntidadeNaoEncontradaException {
		return ResponseEntity.ok(itemEstoqueServico.estoqueDoSupermercado(idSupermercado));
	}
	
	@GetMapping("/parceiros/supermercados/{idSupermercado}/estoque/detalha")
	public ResponseEntity<List<ItemEstoqueDTO>> detalhaEstoqueDoSupermercado(@PathVariable("idSupermercado") Long idSupermercado) throws EntidadeNaoEncontradaException {
		return ResponseEntity.ok(itemEstoqueServico.estoqueDoSupermercado(idSupermercado));
	}
	
	@GetMapping("/parceiros/supermercados/{idSupermercado}/estoque/{idEstoque}")
	public ResponseEntity<ItemEstoqueDTO> porId(@PathVariable("idEstoque") Long idEstoque) throws EntidadeNaoEncontradaException {
		return ResponseEntity.ok(itemEstoqueServico.porId(idEstoque));
	}
	
	@PostMapping("/parceiros/supermercados/{idSupermercado}/estoque")
	public ResponseEntity<ItemEstoqueDTO> adiciona(@RequestBody ItemEstoqueDTO itemEstoque) {
		return ResponseEntity.status(HttpStatus.CREATED).body(itemEstoqueServico.adiciona(itemEstoque));
	}

	@PutMapping("/parceiros/supermercados/{idSupermercado}/estoque/{id}")
	public ResponseEntity<ItemEstoqueDTO> atualiza(@RequestBody ItemEstoqueDTO itemEstoque) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(itemEstoqueServico.atualiza(itemEstoque));
	}

	@DeleteMapping("/parceiros/supermercados/{idSupermercado}/estoque/{id}")
	public ResponseEntity<?> remove(@PathVariable("id") Long id) {
		itemEstoqueServico.remove(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/parceiros/supermercados/{idSupermercado}/estoque//{id}")
	public ResponseEntity<ItemEstoqueDTO> itemEstoquePorId(@PathVariable("id") Long id) throws EntidadeNaoEncontradaException {
		return ResponseEntity.ok(itemEstoqueServico.itemEstoquePorId(id));
	}
	
	@GetMapping("/parceiros/supermercados/{idSupermercado}/estoque/{nome}")
	public ResponseEntity<List<ItemEstoqueDTO>> buscarPorNome(@PathVariable("nome") String nome) throws EntidadeNaoEncontradaException {
		return ResponseEntity.ok(itemEstoqueServico.buscarPorNome(nome));
	}

}
