package com.supermarkt.seguranca;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	public static enum ROLES {
		ADMIN, SUPERMERCADO;
		public String asAuthority() {
			return "ROLE_" + name();
		}
	}

	@Id
	private String authority;

	public String getRole() {
		return authority.replace("ROLE_", "");
	}
}
