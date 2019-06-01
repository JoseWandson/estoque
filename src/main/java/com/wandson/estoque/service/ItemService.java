package com.wandson.estoque.service;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import com.wandson.estoque.dao.ItemDAO;
import com.wandson.estoque.model.Item;
import com.wandson.estoque.model.TipoItem;
import com.wandson.estoque.util.jpa.Transactional;

public class ItemService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ItemDAO itemDAO;

	public Item buscarPeloId(Long id) {
		return itemDAO.buscarPeloId(id);
	}

	@Transactional
	public void salvar(Item item) throws NegocioException {
		itemDAO.salvar(item);
	}

	public List<Item> buscarTodos() {
		return itemDAO.buscarTodos();
	}

	public List<Item> buscarPorTipo(TipoItem tipoItem) {
		if (Objects.isNull(tipoItem)) {
			return buscarTodos();
		}
		return itemDAO.buscarPorTipo(tipoItem);
	}

	@Transactional
	public void excluir(Item item) throws NegocioException {
		try {
			itemDAO.excluir(item);
		} catch (PersistenceException e) {
			throw new NegocioException("Item não pode ser excluído.");
		}
	}

}
