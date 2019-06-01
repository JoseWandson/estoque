package com.wandson.estoque.converter;

import java.text.ParseException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.swing.text.MaskFormatter;

@Named("cepConverter")
public class CepConverter implements Converter<String> {

	@Override
	public String getAsObject(FacesContext context, UIComponent component, String value) {
		return value.replace("-", "");
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, String value) {
		try {
			MaskFormatter mf = new MaskFormatter("#####-###");
			mf.setValueContainsLiteralCharacters(false);
			return mf.valueToString(value);
		} catch (ParseException e) {
			return value;
		}
	}

}
