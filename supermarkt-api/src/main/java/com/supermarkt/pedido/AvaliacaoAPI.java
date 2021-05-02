package com.supermarkt.pedido;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.supermarkt.infra.excecao.EntidadeNaoEncontradaException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AvaliacaoAPI {
	
	private final AvaliacaoServico avaliacaoServico;
	
	@GetMapping("/supermercados/{supermercadoId}/avaliacoes")
	public ResponseEntity<List<AvaliacaoDTO>> listaDoSupermercado(@PathVariable("supermercadoId") Long supermercadoId) throws EntidadeNaoEncontradaException {
		return ResponseEntity.ok(avaliacaoServico.listaDoSupermercado(supermercadoId));
	}

	@PostMapping("/supermercados/{supermercadoId}/avaliacoes")
	public ResponseEntity<AvaliacaoDTO> adiciona(@RequestBody AvaliacaoDTO avaliacao) {
		return ResponseEntity.status(HttpStatus.CREATED).body(avaliacaoServico.adiciona(avaliacao));
	}

	@GetMapping("/supermercados/media-avaliacoes")
	public ResponseEntity<List<MediaAvaliacoesDTO>> mediaDasAvaliacoesDosSupermercados(@RequestParam List<Long> idsDosSupermercados) throws EntidadeNaoEncontradaException {
		return ResponseEntity.ok(avaliacaoServico.mediaDasAvaliacoesDosSupermercados(idsDosSupermercados));
	}

}
