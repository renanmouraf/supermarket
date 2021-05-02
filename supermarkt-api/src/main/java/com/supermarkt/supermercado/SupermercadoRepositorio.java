package com.supermarkt.supermercado;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.supermarkt.seguranca.Usuario;

public interface SupermercadoRepositorio extends JpaRepository<Supermercado, Long>{

	Optional<Supermercado> findByUsuario(Usuario usuario);
	
	Optional<List<Supermercado>> findAllByOrderByNomeAsc();
	
	Optional<List<Supermercado>> findByNomeContainingIgnoreCase(@Param("nome") String nome);
}
