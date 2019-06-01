package com.wandson.estoque.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.wandson.estoque.model.Municipio;
import com.wandson.estoque.service.MunicipioService;
import com.wandson.estoque.service.NegocioException;
import com.wandson.estoque.util.jsf.FacesUtil;

import lombok.Getter;
import lombok.Setter;

@ViewScoped
@Named("pesquisaMunicipio")
public class PesquisaMunicipioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MunicipioService municipioService;

	@Getter
	private List<Municipio> municipios;

	@Getter
	@Setter
	private Municipio municipioSelecionado;

	@PostConstruct
	public void inicializar() {
		municipios = municipioService.buscarTodos();
	}

	public void excluir() {
		try {
			municipioService.excluir(municipioSelecionado);
			municipios.remove(municipioSelecionado);
			FacesUtil.addSuccessMessage("Municipio " + municipioSelecionado.getNome() + " excluído com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

}
