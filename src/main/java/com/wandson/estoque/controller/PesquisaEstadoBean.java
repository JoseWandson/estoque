package com.wandson.estoque.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
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
@Named("pesquisaEstado")
public class PesquisaEstadoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EstadoService estadoService;

	@Getter
	private List<Estado> estados;

	@Getter
	@Setter
	private Estado estadoSelecionado;

	@PostConstruct
	public void inicializar() {
		estados = estadoService.buscarTodos();
	}

	public void excluir() {
		try {
			estadoService.excluir(estadoSelecionado);
			estados.remove(estadoSelecionado);
			FacesUtil.addSuccessMessage("Estado " + estadoSelecionado.getNome() + " excluído com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

}
