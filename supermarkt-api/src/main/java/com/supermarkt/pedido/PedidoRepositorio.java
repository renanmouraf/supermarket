package com.supermarkt.pedido;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

interface PedidoRepositorio extends JpaRepository<Pedido, Long> {

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update Pedido p set p.situacao = :situacao where p = :pedido")
	void atualizaStatus(Pedido.Situacao situacao, Pedido pedido);

	@Query("select p from Pedido p where p.supermercado.id = :supermercadoId and p.situacao not in :listaDeSituacao")
	List<Pedido> doSupermercadoSemSituacao(Long supermercadoId, List<Pedido.Situacao> listaDeSituacao);

	@Query(value = "SELECT p from Pedido p LEFT JOIN FETCH p.itens where p.id = :id") 
	Pedido porIdComItens(Long id);

}
