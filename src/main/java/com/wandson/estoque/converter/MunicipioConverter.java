package com.wandson.estoque.converter;

import java.util.Objects;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import com.mysql.cj.util.StringUtils;
import com.wandson.estoque.model.Municipio;
import com.wandson.estoque.service.MunicipioService;

@Named("municipioConverter")
public class MunicipioConverter implements Converter<Municipio> {

	@Inject
	private MunicipioService municipioService;

	@Override
	public Municipio getAsObject(FacesContext context, UIComponent component, String value) {
		Municipio retorno = new Municipio();

		if (!StringUtils.isEmptyOrWhitespaceOnly(value)) {
			retorno = municipioService.buscarPeloId(Long.parseLong(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Municipio value) {
		if (Objects.nonNull(value)) {
			return Objects.toString(value.getId(), null);
		}

		return "";
	}

}
