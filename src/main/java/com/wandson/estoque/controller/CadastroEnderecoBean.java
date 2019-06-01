package com.wandson.estoque.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.wandson.estoque.model.Endereco;
import com.wandson.estoque.model.Estado;
import com.wandson.estoque.model.Municipio;
import com.wandson.estoque.service.EnderecoService;
import com.wandson.estoque.service.EstadoService;
import com.wandson.estoque.service.MunicipioService;
import com.wandson.estoque.service.NegocioException;
import com.wandson.estoque.util.jsf.FacesUtil;

import lombok.Getter;
import lombok.Setter;

@ViewScoped
@Named("cadastroEndereco")
public class CadastroEnderecoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private Endereco endereco;

	@Getter
	private List<Estado> estados;

	@Getter
	private List<Municipio> municipios;

	@Inject
	private EnderecoService enderecoService;

	@Inject
	private EstadoService estadoService;

	@Inject
	private MunicipioService municipioService;

	@PostConstruct
	public void inicializar() {
		limpar();
		estados = estadoService.buscarTodos();
	}

	public void salvar() {
		try {
			validar();
			enderecoService.salvar(endereco);
			FacesUtil.addSuccessMessage("Endereço salvo com sucesso!");
			if (Objects.isNull(endereco.getId())) {
				limpar();
			}
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public void carregarMunicipios() {
		municipios = municipioService.buscarPorEstado(endereco.getMunicipio().getEstado());
	}

	private void limpar() {
		endereco = new Endereco();
	}

	private void validar() throws NegocioException {
		if (enderecoService.existe(endereco)) {
			throw new NegocioException("Endereço já cadastrado!");
		}
	}

}
