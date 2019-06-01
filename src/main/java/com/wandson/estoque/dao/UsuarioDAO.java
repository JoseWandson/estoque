package com.wandson.estoque.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.wandson.estoque.model.Usuario;

public class UsuarioDAO {

	@Inject
	private EntityManager manager;

	public Usuario buscarPeloId(Long id) {
		return manager.find(Usuario.class, id);
	}

	public void salvar(Usuario usuario) {
		manager.merge(usuario);
	}

	public List<Usuario> buscarTodos() {
		return manager.createQuery("from Usuario", Usuario.class).getResultList();
	}

	public List<Usuario> buscarPorNome(String nome) {
		return manager.createQuery("from Usuario where nome like :nome", Usuario.class)
				.setParameter("nome", "%" + nome + "%").getResultList();
	}

	public void excluir(Usuario usuario) {
		usuario = buscarPeloId(usuario.getId());
		manager.remove(usuario);
		manager.flush();
	}

}
