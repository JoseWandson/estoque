package com.wandson.estoque.util.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

public class FacesUtil {

	private FacesUtil() {
	}

	public static void addSuccessMessage(String message) {
		recuperaFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
	}

	public static void addErrorMessage(String message) {
		recuperaFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
	}

	public static UIInput recuperarInput(String id) {
		return (UIInput) recuperaFacesContext().getViewRoot().findComponent(id);
	}

	private static FacesContext recuperaFacesContext() {
		return FacesContext.getCurrentInstance();
	}

}