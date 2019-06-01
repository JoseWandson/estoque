package com.wandson.estoque.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.wandson.estoque.model.Item;
import com.wandson.estoque.model.TipoItem;
import com.wandson.estoque.service.ItemService;
import com.wandson.estoque.service.NegocioException;
import com.wandson.estoque.util.jsf.FacesUtil;

import lombok.Getter;
import lombok.Setter;

@ViewScoped
@Named("pesquisaItem")
public class PesquisaItemBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ItemService itemService;

	@Getter
	private List<Item> itens;

	@Getter
	@Setter
	private Item itemSelecionado;

	@Getter
	@Setter
	private TipoItem tipo;

	@PostConstruct
	public void inicializar() {
		itens = itemService.buscarTodos();
	}

	public void excluir() {
		try {
			itemService.excluir(itemSelecionado);
			itens.remove(itemSelecionado);
			FacesUtil.addSuccessMessage("Item excluído com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public void filtrar() {
		itens = itemService.buscarPorTipo(tipo);
	}

	public TipoItem[] getTipos() {
		return TipoItem.values();
	}

}
