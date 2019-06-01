package com.wandson.estoque.controller;

import java.io.Serializable;
import java.util.Objects;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.wandson.estoque.model.Estado;
import com.wandson.estoque.service.EstadoService;
import com.wandson.estoque.service.NegocioException;
import com.wandson.estoque.util.jsf.FacesUtil;

import lombok.Getter;
import lombok.Setter;

@ViewScoped
@Named("cadastroEstado")
public class CadastroEstadoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private Estado estado;

	@Inject
	private EstadoService estadoService;

	public CadastroEstadoBean() {
		limpar();
	}

	public void salvar() {
		try {
			estadoService.salvar(estado);
			FacesUtil.addSuccessMessage("Estado salvo com sucesso!");
			if (Objects.isNull(estado.getId())) {
				limpar();
			}
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	private void limpar() {
		estado = new Estado();
	}

}
