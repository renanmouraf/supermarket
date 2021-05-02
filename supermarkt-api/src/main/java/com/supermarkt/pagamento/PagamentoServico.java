package com.supermarkt.pagamento;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.supermarkt.infra.excecao.EntidadeNaoEncontradaException;
import com.supermarkt.pedido.Pedido;
import com.supermarkt.pedido.PedidoMapper;
import com.supermarkt.pedido.PedidoServico;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PagamentoServico {
	
	private final PedidoServico pedidoServico;
	private final PagamentoRepositorio pagamentoRepo;
	private final PagamentoMapper pagamentoMapper;
	private final SimpMessagingTemplate websocket;
	private final PedidoMapper pedidoMapper;
	
	public PagamentoDTO detalha(Long id) {
		Pagamento pagamento = pagamentoRepo.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(Pagamento.class, "id", id.toString()));
		return pagamentoMapper.paraPagamentoDto(pagamento);
	}

	public PagamentoDTO cria(Pagamento pagamento) {
		pagamento.setSituacao(Pagamento.Situacao.CRIADO);
		Pagamento salvo = pagamentoRepo.save(pagamento);
		return pagamentoMapper.paraPagamentoDto(salvo);
	}

	public PagamentoDTO confirma(Long id) {
		Pagamento pagamento = pagamentoRepo.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(Pagamento.class, "id", id.toString()));
		pagamento.setSituacao(Pagamento.Situacao.CONFIRMADO);
		pagamentoRepo.save(pagamento);
		Long pedidoId = pagamento.getPedido().getId();
		Pedido pedido = pedidoServico.porIdComItens(pedidoId);
		pedido.setSituacao(Pedido.Situacao.PAGO);
		pedidoServico.atualizaStatus(Pedido.Situacao.PAGO, pedido);
		websocket.convertAndSend("/parceiros/supermercados/"+pedido.getSupermercado().getId()+"/pedidos/pendentes", pedidoMapper.paraPedidoDto(pedido));
		return pagamentoMapper.paraPagamentoDto(pagamento);
	}

	public PagamentoDTO cancela(Long id) {
		Pagamento pagamento = pagamentoRepo.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(Pagamento.class, "id", id.toString()));
		pagamento.setSituacao(Pagamento.Situacao.CANCELADO);
		pagamentoRepo.save(pagamento);
		return pagamentoMapper.paraPagamentoDto(pagamento);
	}


}
