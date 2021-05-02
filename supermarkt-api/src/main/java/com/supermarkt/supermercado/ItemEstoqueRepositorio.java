package com.supermarkt.supermercado;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemEstoqueRepositorio extends JpaRepository<ItemEstoque, Long> {
	
	Optional<List<ItemEstoque>> findAllBySupermercado(Supermercado supermercado);

	Optional<List<ItemEstoque>> findByNomeContainingIgnoreCase(String nome);

}
