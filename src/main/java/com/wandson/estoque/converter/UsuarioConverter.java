package com.wandson.estoque.converter;

import java.util.Objects;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import com.mysql.cj.util.StringUtils;
import com.wandson.estoque.model.Usuario;
import com.wandson.estoque.service.UsuarioService;

@Named("usuarioConverter")
public class UsuarioConverter implements Converter<Usuario> {

	@Inject
	private UsuarioService usuarioService;

	@Override
	public Usuario getAsObject(FacesContext context, UIComponent component, String value) {
		Usuario retorno = new Usuario();

		if (!StringUtils.isEmptyOrWhitespaceOnly(value)) {
			retorno = usuarioService.buscarPeloId(Long.parseLong(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Usuario value) {
		if (Objects.nonNull(value)) {
			return Objects.toString(value.getId(), null);
		}

		return "";
	}

}
