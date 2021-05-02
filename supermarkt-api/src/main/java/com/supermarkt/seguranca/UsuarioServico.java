package com.supermarkt.seguranca;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioServico implements UserDetailsService {
	
	private UsuarioRepositorio usuarioRepositorio;
	private BCryptPasswordEncoder codificador;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepositorio.findByName(username);
		return usuario.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
	}
	
	public UserDetails loadUserById(Long userId) {
		Optional<Usuario> usuario = usuarioRepositorio.findById(userId);
		return usuario.orElseThrow(() -> new UsernameNotFoundException("Não foi possível encontrar o usuário com id: " + userId));
	}
	
	public Usuario salvar(Usuario usuario) {
		usuario.setPassword(codificador.encode(usuario.getPassword()));
		return usuarioRepositorio.save(usuario);
	}

}
