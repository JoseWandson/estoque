package com.wandson.estoque.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.wandson.estoque.model.Estado;
import com.wandson.estoque.model.Municipio;

public class MunicipioDAO {

	@Inject
	private EntityManager manager;

	public Municipio buscarPeloId(Long id) {
		return manager.find(Municipio.class, id);
	}

	public void salvar(Municipio municipio) {
		manager.merge(municipio);
	}

	public List<Municipio> buscarTodos() {
		return manager.createQuery("from Municipio", Municipio.class).getResultList();
	}

	public void excluir(Municipio municipio) {
		municipio = buscarPeloId(municipio.getId());
		manager.remove(municipio);
		manager.flush();
	}

	public Boolean existeNoEstado(Municipio municipio) {
		try {
			manager.createQuery("from Municipio where estado = :estado and :nome = nome", Municipio.class)
					.setParameter("estado", municipio.getEstado()).setParameter("nome", municipio.getNome())
					.getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}

	public List<Municipio> buscarPorEstado(Estado estado) {
		return manager.createQuery("from Municipio where estado = :estado", Municipio.class)
				.setParameter("estado", estado).getResultList();
	}

}
