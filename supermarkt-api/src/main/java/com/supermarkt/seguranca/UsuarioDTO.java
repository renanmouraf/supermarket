package com.supermarkt.seguranca;

import lombok.Data;

@Data
public class UsuarioDTO {

	private String username;
	private String password;
	
	public Usuario toUsuario() {
		return new Usuario(username, password);
	}

}
