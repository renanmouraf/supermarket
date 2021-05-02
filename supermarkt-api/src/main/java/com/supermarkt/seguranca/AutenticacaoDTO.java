package com.supermarkt.seguranca;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AutenticacaoDTO {
	
	private String username;
	private List<String> roles;
	private String token;
	private Long targetId;

	public AutenticacaoDTO(Usuario usuario, String jwtToken, Long targetId) {
		this(usuario.getName(), usuario.getRoles(), jwtToken, targetId);
	}

}
