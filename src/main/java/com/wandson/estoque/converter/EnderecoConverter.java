package com.wandson.estoque.converter;

import java.util.Objects;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import com.mysql.cj.util.StringUtils;
import com.wandson.estoque.model.Endereco;
import com.wandson.estoque.service.EnderecoService;

@Named("enderecoConverter")
public class EnderecoConverter implements Converter<Endereco> {

	@Inject
	private EnderecoService enderecoService;

	@Override
	public Endereco getAsObject(FacesContext context, UIComponent component, String value) {
		Endereco retorno = new Endereco();

		if (!StringUtils.isEmptyOrWhitespaceOnly(value)) {
			retorno = enderecoService.buscarPeloId(Long.parseLong(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Endereco value) {
		if (Objects.nonNull(value)) {
			return Objects.toString(value.getId(), null);
		}

		return "";
	}

}
