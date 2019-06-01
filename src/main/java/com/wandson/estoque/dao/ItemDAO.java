package com.wandson.estoque.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.wandson.estoque.model.Item;
import com.wandson.estoque.model.TipoItem;

public class ItemDAO {

	@Inject
	private EntityManager manager;

	public Item buscarPeloId(Long id) {
		return manager.find(Item.class, id);
	}

	public void salvar(Item item) {
		manager.merge(item);
	}

	public List<Item> buscarTodos() {
		return manager.createQuery("from Item", Item.class).getResultList();
	}

	public List<Item> buscarPorTipo(TipoItem tipoItem) {
		return manager.createQuery("from Item where tipoItem = :tipoItem", Item.class)
				.setParameter("tipoItem", tipoItem).getResultList();
	}

	public void excluir(Item item) {
		item = buscarPeloId(item.getId());
		manager.remove(item);
		manager.flush();
	}

}
