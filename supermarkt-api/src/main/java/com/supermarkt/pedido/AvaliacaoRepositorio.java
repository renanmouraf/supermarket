package com.supermarkt.pedido;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.supermarkt.supermercado.Supermercado;

public interface AvaliacaoRepositorio  extends JpaRepository<Avaliacao, Long> {

	@Query("select avg(a.nota) from Avaliacao a where a.pedido.supermercado.id =:supermercadoId")
	Double mediaDoSupermercadoPeloId(Long supermercadoId);

	@Query("select a from Avaliacao a where a.pedido.supermercado = :supermercado")
	Optional<List<Avaliacao>> findAllBySupermercado(Supermercado supermercado);


}
