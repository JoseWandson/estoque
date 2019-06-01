package com.wandson.estoque.converter;

import java.util.Objects;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import com.mysql.cj.util.StringUtils;
import com.wandson.estoque.model.Item;
import com.wandson.estoque.service.ItemService;

@Named("itemConverter")
public class ItemConverter implements Converter<Item> {

	@Inject
	private ItemService itemService;

	@Override
	public Item getAsObject(FacesContext context, UIComponent component, String value) {
		Item retorno = new Item();

		if (!StringUtils.isEmptyOrWhitespaceOnly(value)) {
			retorno = itemService.buscarPeloId(Long.parseLong(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Item value) {
		if (Objects.nonNull(value)) {
			return Objects.toString(value.getId(), null);
		}

		return "";
	}

}
