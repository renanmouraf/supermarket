package com.supermarkt.supermercado;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.supermarkt.admin.TipoPagamento;
import com.supermarkt.infra.excecao.EntidadeNaoEncontradaException;
import com.supermarkt.supermercado.SupermercadoTipoPagamento.SupermercadoTipoPagamentoId;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SupermercadoTipoPagamentoServico {

	private final SupermercadoTipoPagamentoRepositorio supermercadoTipoPagamentoRepo;
	
	public void adiciona(Long idRestaurante, @RequestBody TipoPagamento tipoPagamento) {
		SupermercadoTipoPagamentoId id = new SupermercadoTipoPagamentoId(idRestaurante, tipoPagamento.getId());
		Supermercado supermercado = new Supermercado();
		supermercado.setId(idRestaurante);
		SupermercadoTipoPagamento supermercadoTipoPagamento = new SupermercadoTipoPagamento(id, supermercado,
				tipoPagamento);
		supermercadoTipoPagamentoRepo.save(supermercadoTipoPagamento);
	}

	public void removeDoRestaurante(Long idRestaurante, Long idFormaDePagamento) {
		SupermercadoTipoPagamentoId id = new SupermercadoTipoPagamentoId(idRestaurante, idFormaDePagamento);
		supermercadoTipoPagamentoRepo.deleteById(id);
	}

	public List<TipoPagamento> lista(Long idSupermercado) {
		Supermercado supermercado = new Supermercado();
		supermercado.setId(idSupermercado);
		List<TipoPagamento> lista;
		try {
			lista = supermercadoTipoPagamentoRepo.findAllBySupermercadoOrderByNomeAsc(supermercado);
		}catch (Exception e) {
			throw new EntidadeNaoEncontradaException(ItemEstoque.class, "idSupermercado", idSupermercado.toString());
		}
		return lista;
	}
	
}
