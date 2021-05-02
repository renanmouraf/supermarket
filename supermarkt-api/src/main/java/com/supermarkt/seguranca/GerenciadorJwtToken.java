package com.supermarkt.seguranca;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class GerenciadorJwtToken {
	
	private String segredo;
	private long expiracaoEmMillis;
	
	public GerenciadorJwtToken(@Value("${jwt.segredo}") String segredo, 
							   @Value("${jwt.expiracao}") long expiracaoEmMillis) {
		this.segredo = segredo;
		this.expiracaoEmMillis = expiracaoEmMillis;
	}
	
	public String gerarToken(Usuario usuario) {
		final Date now = new Date();
		final Date expiracao = new Date(now.getTime() + this.expiracaoEmMillis);
		return Jwts.builder().setIssuer("Supermarkt").setSubject(Long.toString(usuario.getId())).setIssuedAt(now)
				.setExpiration(expiracao).signWith(SignatureAlgorithm.HS256, this.segredo).compact();
	}
	
	public boolean isValido(String jwt) {
		try {
			Jwts.parser().setSigningKey(this.segredo).parseClaimsJws(jwt);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}
	
	public Long getIdUsuarioDoToken(String jwt) {
		Claims  claims = Jwts.parser().setSigningKey(this.segredo).parseClaimsJws(jwt).getBody();
		return Long.parseLong(claims.getSubject());
	}

}
