package com.supermarkt.supermercado;

import java.util.List;

import org.springframework.stereotype.Service;

import com.supermarkt.infra.excecao.EntidadeNaoEncontradaException;
import com.supermarkt.pedido.Pedido;
import com.supermarkt.seguranca.Role.ROLES;
import com.supermarkt.seguranca.Usuario;
import com.supermarkt.seguranca.UsuarioServico;

import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;

@Service
@RequiredArgsConstructor
public class SupermercadoServico {
	
	@Delegate
	private final SupermercadoRepositorio supermercadoRepo;
	private final SupermercadoMapper supermercadoMapper;
	private final UsuarioServico usuarioServico;
	
	public List<SupermercadoDTO> lista() {
		List<Supermercado> lista = supermercadoRepo.findAllByOrderByNomeAsc().orElseThrow(() -> new EntidadeNaoEncontradaException(Supermercado.class));
		return supermercadoMapper.paraSupermercadoDto(lista);
	}
	
	public List<SupermercadoDTO> buscarPorNome(String nome) {
		List<Supermercado> lista = supermercadoRepo.findByNomeContainingIgnoreCase(nome).orElseThrow(() -> new EntidadeNaoEncontradaException(Supermercado.class));
		return supermercadoMapper.paraSupermercadoDto(lista);
	}
	
	public SupermercadoDTO detalha(Long id) {
		Supermercado supermercado = supermercadoRepo.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(Supermercado.class, "id", id.toString()));
		return supermercadoMapper.paraSupermercadoDto(supermercado);
	}
	
	public List<SupermercadoDTO> detalhePorIds(List<Long> ids) {
		List<Supermercado> lista;
		try {
			lista = supermercadoRepo.findAllById(ids);
		} catch (EntidadeNaoEncontradaException e) {
			throw new EntidadeNaoEncontradaException(Pedido.class, "ids", ids.toString());
		}
		return supermercadoMapper.paraSupermercadoDto(lista);
	}
	
	public void remove(Long id) {
		supermercadoRepo.deleteById(id);
	}
	
	public SupermercadoDTO detalhaParceiro(Long id) {
		Supermercado supermercado = supermercadoRepo.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(Supermercado.class, "id", id.toString()));
		return supermercadoMapper.paraSupermercadoDto(supermercado);
	}

	public Supermercado adiciona(Supermercado supermercado) {
		supermercado.setFavorito(false);
		Supermercado supermercadoSalvo = supermercadoRepo.save(supermercado);
		
		Usuario usuario = new Usuario(supermercado.getNome().replaceAll("\\s+","").toLowerCase()+supermercado.getId(), "123456");
		usuario.addRole(ROLES.SUPERMERCADO);
		usuario = usuarioServico.salvar(usuario);
		supermercadoSalvo.setUsuario(usuario);
		supermercadoRepo.save(supermercadoSalvo);
		return supermercadoSalvo;
	}

	public Supermercado atualiza(Supermercado supermercado) {
		System.out.println(supermercado);
		Supermercado doBD = supermercadoRepo.getOne(supermercado.getId());
		supermercado.setUsuario(doBD.getUsuario());
		return supermercadoRepo.save(supermercado);
	}

}
