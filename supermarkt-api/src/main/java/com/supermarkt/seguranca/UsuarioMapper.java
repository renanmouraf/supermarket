package com.supermarkt.seguranca;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
	
	Usuario paraUsuario(UsuarioDTO usuarioDto);
	
	UsuarioDTO paraUsuarioDto(Usuario usuario);
	
	List<UsuarioDTO> paraUsuarioDto(List<Usuario> usuario);
	
	List<Usuario> paraUsuario(List<UsuarioDTO> usuarioDto);

}
