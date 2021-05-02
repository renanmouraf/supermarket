package com.supermarkt.supermercado;

import java.util.List;

import org.springframework.stereotype.Service;

import com.supermarkt.infra.excecao.EntidadeNaoEncontradaException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ItemEstoqueServico {
	
	private final ItemEstoqueRepositorio repo;
	private final ItemEstoqueMapper itemEstoqueMapper;
	
	public List<ItemEstoqueDTO> estoqueDoSupermercado(Long idSupermercado) {
		Supermercado supermercado = new Supermercado();
		supermercado.setId(idSupermercado);
		List<ItemEstoque> lista = repo.findAllBySupermercado(supermercado).orElseThrow(() -> new EntidadeNaoEncontradaException(ItemEstoque.class, "nome", idSupermercado.toString()));
		return itemEstoqueMapper.paraItemEstoqueDto(lista);
	}
	
	public ItemEstoqueDTO porId(Long idEstoque) {
		ItemEstoque estoque = repo.findById(idEstoque).orElseThrow(() -> new EntidadeNaoEncontradaException(ItemEstoque.class, "id", idEstoque.toString()));
		return itemEstoqueMapper.paraItemEstoqueDto(estoque);
	}
	
	public ItemEstoqueDTO adiciona(ItemEstoqueDTO itemEstoqueDto) {
		return itemEstoqueMapper.paraItemEstoqueDto(repo.save(itemEstoqueMapper.paraItemEstoque(itemEstoqueDto)));
	}

	public ItemEstoqueDTO atualiza(ItemEstoqueDTO itemEstoqueDto) {
		return itemEstoqueMapper.paraItemEstoqueDto(repo.save(itemEstoqueMapper.paraItemEstoque(itemEstoqueDto)));
	}

	public void remove(Long id) {
		repo.deleteById(id);
	}
	
	public ItemEstoqueDTO itemEstoquePorId(Long id) {
		ItemEstoque itemEstoque = repo.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(ItemEstoque.class, "id", id.toString()));
		return itemEstoqueMapper.paraItemEstoqueDto(itemEstoque); 
	}
	
	public List<ItemEstoqueDTO> buscarPorNome(String nome) {
		List<ItemEstoque> lista = repo.findByNomeContainingIgnoreCase(nome).orElseThrow(() -> new EntidadeNaoEncontradaException(ItemEstoque.class, "nome", nome));
		return itemEstoqueMapper.paraItemEstoqueDto(lista);
	}


}
