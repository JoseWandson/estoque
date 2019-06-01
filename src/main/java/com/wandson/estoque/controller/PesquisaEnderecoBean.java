package com.wandson.estoque.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.wandson.estoque.model.Endereco;
import com.wandson.estoque.service.EnderecoService;
import com.wandson.estoque.service.NegocioException;
import com.wandson.estoque.util.jsf.FacesUtil;

import lombok.Getter;
import lombok.Setter;

@ViewScoped
@Named("pesquisaEndereco")
public class PesquisaEnderecoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EnderecoService enderecoService;

	@Getter
	private List<Endereco> enderecos;

	@Getter
	@Setter
	private Endereco enderecoSelecionado;

	@Getter
	@Setter
	private String cep;

	@PostConstruct
	public void inicializar() {
		enderecos = enderecoService.buscarTodos();
	}

	public void excluir() {
		try {
			enderecoService.excluir(enderecoSelecionado);
			enderecos.remove(enderecoSelecionado);
			FacesUtil.addSuccessMessage("Endereco excluído com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public void filtrar() {
		enderecos = enderecoService.buscarPorCep(cep);
	}

}
