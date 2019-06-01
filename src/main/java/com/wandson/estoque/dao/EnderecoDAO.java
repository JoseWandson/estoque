package com.wandson.estoque.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.wandson.estoque.model.Endereco;
import com.wandson.estoque.model.Estado;
import com.wandson.estoque.model.Municipio;

public class EnderecoDAO {

	@Inject
	private EntityManager manager;

	public Endereco buscarPeloId(Long id) {
		return manager.find(Endereco.class, id);
	}

	public void salvar(Endereco endereco) {
		manager.merge(endereco);
	}

	public List<Endereco> buscarTodos() {
		return manager.createQuery("from Endereco", Endereco.class).getResultList();
	}

	public List<Endereco> buscarPorCep(String cep) {
		return manager.createQuery("from Endereco where cep = :cep", Endereco.class).setParameter("cep", cep)
				.getResultList();
	}

	public void excluir(Endereco endereco) {
		endereco = buscarPeloId(endereco.getId());
		manager.remove(endereco);
		manager.flush();
	}

	public Boolean existe(Endereco endereco) {
		try {
			CriteriaBuilder builder = manager.getCriteriaBuilder();
			CriteriaQuery<Endereco> criteriaQuery = builder.createQuery(Endereco.class);

			Root<Endereco> enderecoRoot = criteriaQuery.from(Endereco.class);
			Join<Endereco, Municipio> municipio = enderecoRoot.join("municipio");
			Join<Municipio, Estado> estado = municipio.join("estado");

			criteriaQuery.select(enderecoRoot);

			List<Predicate> predicates = new ArrayList<>();
			predicates.add(builder.equal(enderecoRoot.get("cep"), endereco.getCep()));
			predicates.add(builder.equal(enderecoRoot.get("logradouro"), endereco.getLogradouro()));
			predicates.add(builder.equal(enderecoRoot.get("numero"), endereco.getNumero()));
			predicates.add(builder.equal(municipio, endereco.getMunicipio()));
			predicates.add(builder.equal(estado, endereco.getMunicipio().getEstado()));

			if (Objects.nonNull(endereco.getId())) {
				predicates.add(builder.notEqual(enderecoRoot.get("id"), endereco.getId()));
			}

			criteriaQuery.where(predicates.toArray(new Predicate[0]));

			manager.createQuery(criteriaQuery).getSingleResult();

			return true;
		} catch (NoResultException e) {
			return false;
		}
	}

}
