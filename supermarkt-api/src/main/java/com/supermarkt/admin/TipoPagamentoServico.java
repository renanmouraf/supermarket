package com.supermarkt.admin;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.supermarkt.infra.excecao.EntidadeNaoEncontradaException;
import com.supermarkt.pedido.Pedido;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TipoPagamentoServico {
	
	private final TipoPagamentoRepositorio tipoPagamentoRepo;
	private final TipoPagamentoMapper tipoPagamentoMapper;
	
	public List<TipoPagamentoDTO> listaTodos() {
		List<TipoPagamento> lista = tipoPagamentoRepo.findAllByOrderByNomeAsc().orElseThrow(() -> new EntidadeNaoEncontradaException(TipoPagamento.class));
		return tipoPagamentoMapper.paraTipoPagamentoDto(lista);
	}
	
	public List<FormaPagamentoDTO> formas() {
		return Arrays.asList(TipoPagamento.Forma.values()).stream()
				.map(forma -> new FormaPagamentoDTO(forma)).collect(Collectors.toList());
	}
	
	public TipoPagamentoDTO adiciona(TipoPagamentoDTO tipoPagamentoDto) {
		TipoPagamento tipoPagamento = tipoPagamentoMapper.paraTipoPagamento(tipoPagamentoDto);
		return tipoPagamentoMapper.paraTipoPagamentoDto(tipoPagamentoRepo.save(tipoPagamento));
	}

	public TipoPagamentoDTO atualiza(TipoPagamentoDTO tipoPagamentoDto) {
		TipoPagamento tipoPagamento = tipoPagamentoMapper.paraTipoPagamento(tipoPagamentoDto);
		return tipoPagamentoMapper.paraTipoPagamentoDto(tipoPagamentoRepo.save(tipoPagamento));
	}
	
	public void remove(Long id) {
		tipoPagamentoRepo.deleteById(id);
	}
	
	public TipoPagamentoDTO tipoPorId(Long id) {
		TipoPagamento tipoPagamento = tipoPagamentoRepo.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(Pedido.class, "id", id.toString()));
		return tipoPagamentoMapper.paraTipoPagamentoDto(tipoPagamento);
	}
	
	public List<TipoPagamentoDTO> buscarPorNome(String nome) {
		List<TipoPagamento> lista = tipoPagamentoRepo.findByNomeContainingIgnoreCase(nome).orElseThrow(() -> new EntidadeNaoEncontradaException(TipoPagamento.class, "nome", nome));
		return tipoPagamentoMapper.paraTipoPagamentoDto(lista);
	}

}
