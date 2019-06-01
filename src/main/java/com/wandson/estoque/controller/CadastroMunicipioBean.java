package com.wandson.estoque.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.faces.component.UIInput;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.wandson.estoque.model.Estado;
import com.wandson.estoque.model.Municipio;
import com.wandson.estoque.service.EstadoService;
import com.wandson.estoque.service.MunicipioService;
import com.wandson.estoque.service.NegocioException;
import com.wandson.estoque.util.jsf.FacesUtil;

import lombok.Getter;
import lombok.Setter;

@ViewScoped
@Named("cadastroMunicipio")
public class CadastroMunicipioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private Municipio municipio;

	@Getter
	private List<Estado> estados;

	@Inject
	private MunicipioService municipioService;

	@Inject
	private EstadoService estadoService;

	@PostConstruct
	public void inicializar() {
		limpar();
		estados = estadoService.buscarTodos();
	}

	public void salvar() {
		try {
			validar();
			municipioService.salvar(municipio);
			FacesUtil.addSuccessMessage("Município salvo com sucesso!");
			if (Objects.isNull(municipio.getId())) {
				limpar();
			}
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	private void validar() throws NegocioException {
		if (municipioService.existeNoEstado(municipio)) {
			UIInput uiInput = FacesUtil.recuperarInput("frmCadastro:nome");
			uiInput.setValid(false);
			throw new NegocioException("Município já cadastrado para o estado selecionado!");
		}
	}

	private void limpar() {
		municipio = new Municipio();
	}

}
