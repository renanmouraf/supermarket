package com.supermarkt.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

interface TipoPagamentoRepositorio extends JpaRepository<TipoPagamento, Long> {

	Optional<List<TipoPagamento>> findAllByOrderByNomeAsc();
	
	Optional<List<TipoPagamento>> findByNomeContainingIgnoreCase(@Param("nome") String nome);

}
