package com.wandson.estoque.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import com.wandson.estoque.dao.UsuarioDAO;
import com.wandson.estoque.model.Usuario;
import com.wandson.estoque.util.jpa.Transactional;

public class UsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDAO usuarioDAO;

	public Usuario buscarPeloId(Long id) {
		return usuarioDAO.buscarPeloId(id);
	}

	@Transactional
	public void salvar(Usuario usuario) throws NegocioException {
		try {
			usuarioDAO.salvar(usuario);
		} catch (PersistenceException e) {
			throw new NegocioException("Usuario já cadastrado!");
		}
	}

	public List<Usuario> buscarTodos() {
		return usuarioDAO.buscarTodos();
	}

	public List<Usuario> buscarPorNome(String nome) {
		return usuarioDAO.buscarPorNome(nome);
	}

	@Transactional
	public void excluir(Usuario usuario) throws NegocioException {
		try {
			usuarioDAO.excluir(usuario);
		} catch (PersistenceException e) {
			throw new NegocioException("Usuario não pode ser excluído.");
		}
	}

}
