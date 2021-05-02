package com.supermarkt.supermercado;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.supermarkt.admin.TipoPagamento;

public interface SupermercadoTipoPagamentoRepositorio extends JpaRepository<SupermercadoTipoPagamento, SupermercadoTipoPagamento.SupermercadoTipoPagamentoId> {
	
	@Query("select f from SupermercadoTipoPagamento rf join rf.supermercado r join rf.tipoPagamento f where r = :supermercado order by f.nome")
	List<TipoPagamento> findAllBySupermercadoOrderByNomeAsc(Supermercado supermercado);

}
