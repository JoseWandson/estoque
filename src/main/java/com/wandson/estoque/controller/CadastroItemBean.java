package com.wandson.estoque.controller;

import java.io.Serializable;
import java.util.Objects;

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
@Named("cadastroItem")
public class CadastroItemBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private Item item;

	@Inject
	private ItemService itemService;

	@PostConstruct
	public void inicializar() {
		limpar();
	}

	public void salvar() {
		try {
			itemService.salvar(item);
			FacesUtil.addSuccessMessage("Item salvo com sucesso!");
			if (Objects.isNull(item.getId())) {
				limpar();
			}
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public TipoItem[] getTipos() {
		return TipoItem.values();
	}

	private void limpar() {
		item = new Item();
	}

}
