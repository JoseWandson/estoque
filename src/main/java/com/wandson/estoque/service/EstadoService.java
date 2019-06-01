package com.wandson.estoque.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import com.wandson.estoque.dao.EstadoDAO;
import com.wandson.estoque.model.Estado;
import com.wandson.estoque.util.jpa.Transactional;

public class EstadoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EstadoDAO estadoDAO;

	public Estado buscarPeloId(Long id) {
		return estadoDAO.buscarPeloId(id);
	}

	@Transactional
	public void salvar(Estado estado) throws NegocioException {
		if (estadoDAO.existe(estado)) {
			throw new NegocioException("Estado já cadastrado!");
		}
		estadoDAO.salvar(estado);
	}

	public List<Estado> buscarTodos() {
		return estadoDAO.buscarTodos();
	}

	@Transactional
	public void excluir(Estado estado) throws NegocioException {
		try {
			estadoDAO.excluir(estado);
		} catch (PersistenceException e) {
			throw new NegocioException("Estado não pode ser excluído.");
		}
	}

}
