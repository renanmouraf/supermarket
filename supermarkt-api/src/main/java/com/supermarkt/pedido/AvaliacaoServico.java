package com.supermarkt.pedido;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.supermarkt.infra.excecao.EntidadeNaoEncontradaException;
import com.supermarkt.supermercado.Supermercado;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AvaliacaoServico {
	
	private final AvaliacaoRepositorio repo;
	private final AvaliacaoMapper avaliacaoMapper;
	
	public List<AvaliacaoDTO> listaDoSupermercado(Long supermercadoId) {
		Supermercado supermercado = new Supermercado();
		supermercado.setId(supermercadoId);
		List<Avaliacao> lista = repo.findAllBySupermercado(supermercado).orElseThrow(() -> new EntidadeNaoEncontradaException(Avaliacao.class, "supermercadoId", supermercadoId.toString()));
		return avaliacaoMapper.paraAvaliacaoDto(lista);
	}

	public AvaliacaoDTO adiciona(@RequestBody AvaliacaoDTO avaliacaoDto) {
		Avaliacao avaliacao = avaliacaoMapper.paraAvaliacao(avaliacaoDto);
		Avaliacao salvo = repo.save(avaliacao);
		return avaliacaoMapper.paraAvaliacaoDto(salvo);
	}

	public List<MediaAvaliacoesDTO> mediaDasAvaliacoesDosSupermercados(List<Long> idsDosSupermercados) {
		List<MediaAvaliacoesDTO> medias = new ArrayList<>();
		for (Long supermercadoId : idsDosSupermercados) {
			Double media = repo.mediaDoSupermercadoPeloId(supermercadoId);
			medias.add(new MediaAvaliacoesDTO(supermercadoId, media));
		}
		return medias;
	}


}
