package com.supermarkt.seguranca;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AutenticacaoServico {
	
	private final AuthenticationManager authManager;
	private final GerenciadorJwtToken gerenciadorJwtToken;
	private final UsuarioServico usuarioServico;
	private final List<AutorizacaoTargetServico> autorizacaoTargetServicos;
	
	public AutenticacaoDTO authenticate(UsuarioDTO login) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				login.getUsername(), login.getPassword());

		Authentication authentication = authManager.authenticate(authenticationToken);
		Usuario usuario = (Usuario) authentication.getPrincipal();
		String jwt = gerenciadorJwtToken.gerarToken(usuario);
		Long targetId = getTargetIdFor(usuario);
		AutenticacaoDTO tokenResponse = new AutenticacaoDTO(usuario, jwt, targetId);
		return tokenResponse;

	}
	
	public Long register(UsuarioDTO usuarioDto) {
		Usuario usuario = usuarioDto.toUsuario();
		usuario.addRole(Role.ROLES.SUPERMERCADO);
		Usuario salvo = usuarioServico.salvar(usuario);
		return salvo.getId();
	}

	private Long getTargetIdFor(Usuario usuario) {
		for (AutorizacaoTargetServico autorizacaoTargetServico : autorizacaoTargetServicos) {
			Long targetId = autorizacaoTargetServico.getTargetIdByUser(usuario);
			if (targetId != null) {
				return targetId;
			}
		}
		return null;
	}
}