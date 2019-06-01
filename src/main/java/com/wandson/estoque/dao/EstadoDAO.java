package com.wandson.estoque.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.wandson.estoque.model.Estado;

public class EstadoDAO {

	@Inject
	private EntityManager manager;

	public Estado buscarPeloId(Long id) {
		return manager.find(Estado.class, id);
	}

	public void salvar(Estado estado) {
		manager.merge(estado);
	}

	public List<Estado> buscarTodos() {
		return manager.createQuery("from Estado", Estado.class).getResultList();
	}

	public void excluir(Estado estado) {
		estado = buscarPeloId(estado.getId());
		manager.remove(estado);
		manager.flush();
	}

	public Boolean existe(Estado estado) {
		try {
			CriteriaBuilder builder = manager.getCriteriaBuilder();
			CriteriaQuery<Estado> criteriaQuery = builder.createQuery(Estado.class);

			Root<Estado> estadoRoot = criteriaQuery.from(Estado.class);

			criteriaQuery.select(estadoRoot);

			List<Predicate> predicates = new ArrayList<>();
			predicates.add(builder.equal(estadoRoot.get("nome"), estado.getNome()));

			if (Objects.nonNull(estado.getId())) {
				predicates.add(builder.notEqual(estadoRoot.get("id"), estado.getId()));
			}

			criteriaQuery.where(predicates.toArray(new Predicate[0]));

			manager.createQuery(criteriaQuery).getSingleResult();

			return true;
		} catch (NoResultException e) {
			return false;
		}
	}

}
