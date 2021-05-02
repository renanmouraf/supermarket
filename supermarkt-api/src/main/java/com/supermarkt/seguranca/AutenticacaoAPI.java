package com.supermarkt.seguranca;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/autenticacao")
@RequiredArgsConstructor
public class AutenticacaoAPI {
	
	private final AutenticacaoServico autenticacaoServico;
	
	@PostMapping
	public ResponseEntity<AutenticacaoDTO> authenticate(@RequestBody UsuarioDTO login) {
		try {
			return ResponseEntity.ok(autenticacaoServico.authenticate(login));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping("/supermercado")
	public ResponseEntity<Long> register(@RequestBody UsuarioDTO usuarioDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(autenticacaoServico.register(usuarioDto));
	}

}
