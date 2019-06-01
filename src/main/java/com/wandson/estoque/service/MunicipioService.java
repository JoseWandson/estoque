package com.wandson.estoque.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import com.wandson.estoque.dao.MunicipioDAO;
import com.wandson.estoque.model.Estado;
import com.wandson.estoque.model.Municipio;
import com.wandson.estoque.util.jpa.Transactional;

public class MunicipioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MunicipioDAO municipioDAO;

	public Municipio buscarPeloId(Long id) {
		return municipioDAO.buscarPeloId(id);
	}

	@Transactional
	public void salvar(Municipio municipio) {
		municipioDAO.salvar(municipio);
	}

	public List<Municipio> buscarTodos() {
		return municipioDAO.buscarTodos();
	}

	@Transactional
	public void excluir(Municipio municipio) throws NegocioException {
		try {
			municipioDAO.excluir(municipio);
		} catch (PersistenceException e) {
			throw new NegocioException("Municipio não pode ser excluído.");
		}
	}

	public Boolean existeNoEstado(Municipio municipio) {
		return municipioDAO.existeNoEstado(municipio);
	}

	public List<Municipio> buscarPorEstado(Estado estado) {
		return municipioDAO.buscarPorEstado(estado);
	}

}
