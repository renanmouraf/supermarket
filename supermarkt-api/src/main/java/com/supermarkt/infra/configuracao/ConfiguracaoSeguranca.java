package com.supermarkt.infra.configuracao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.supermarkt.seguranca.FiltroAutenticacaoJwt;
import com.supermarkt.seguranca.JwtAuthenticationEntryPoint;
import com.supermarkt.seguranca.Role;
import com.supermarkt.seguranca.UsuarioServico;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class ConfiguracaoSeguranca extends WebSecurityConfigurerAdapter {

	private UsuarioServico usuarioServico;
	private FiltroAutenticacaoJwt filtroAutenticacaoJwt;
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/supermercados/**", "/pedidos/**", "/pagamentos/**", "/tipo-pagamento/**").permitAll()
			.antMatchers("/h2/**").permitAll() //liberar o h2 console, não recomendado para produção
			.antMatchers("/socket/**").permitAll()
			.antMatchers("/autenticacao/**").permitAll()
			.antMatchers("/admin/**").hasRole(Role.ROLES.ADMIN.name())
			.antMatchers(HttpMethod.POST, "/parceiros/supermercados").permitAll()
			.antMatchers("/supermercados/{supermercadoId}/estoque").permitAll()
			.antMatchers("/parceiros/supermercados/{supermercadoId}/estoque/**")
						.access("@autorizacaoServico.checarTargetId(authentication,#supermercadoId) or hasRole('ADMIN')")
			.antMatchers("/parceiros/supermercados/{supermercadoId}/**").access("hasRole('ADMIN') or @autorizacaoServico.checarTargetId(authentication,#supermercadoId)")
			.antMatchers("/parceiros/**").hasRole(Role.ROLES.SUPERMERCADO.name())
			.anyRequest().authenticated()
			.and().headers().frameOptions().disable() //apenas para uso do h2 console, não recomendado para produção
			.and().cors()
			.and().csrf().disable()
			.formLogin().disable()
			.httpBasic().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.addFilterBefore(filtroAutenticacaoJwt, UsernamePasswordAuthenticationFilter.class)
			.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);
		
		
	}
	
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioServico).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
