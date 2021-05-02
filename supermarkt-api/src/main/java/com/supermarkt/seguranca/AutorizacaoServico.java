package com.supermarkt.seguranca;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AutorizacaoServico {

	private List<AutorizacaoTargetServico> autorizacaoTargetServicos;
	
	public boolean checarTargetId(Authentication authentication, long id) {
		Usuario usuario = (Usuario) authentication.getPrincipal();
		for(AutorizacaoTargetServico autorizacaoTargetServico: autorizacaoTargetServicos) {
			Long targetId = autorizacaoTargetServico.getTargetIdByUser(usuario);
			if(targetId != null && targetId == id) {
				return true;
			}
		}
		return false;
	}
}
