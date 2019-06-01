package com.wandson.estoque.converter;

import java.util.Objects;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import com.mysql.cj.util.StringUtils;
import com.wandson.estoque.model.Estado;
import com.wandson.estoque.service.EstadoService;

@Named("estadoConverter")
public class EstadoConverter implements Converter<Estado> {

	@Inject
	private EstadoService estadoService;

	@Override
	public Estado getAsObject(FacesContext context, UIComponent component, String value) {
		Estado retorno = new Estado();

		if (!StringUtils.isEmptyOrWhitespaceOnly(value)) {
			retorno = estadoService.buscarPeloId(Long.parseLong(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Estado value) {
		if (Objects.nonNull(value)) {
			return Objects.toString(value.getId(), null);
		}

		return "";
	}

}
