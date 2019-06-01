package com.wandson.estoque.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.wandson.estoque.model.Usuario;
import com.wandson.estoque.service.NegocioException;
import com.wandson.estoque.service.UsuarioService;
import com.wandson.estoque.util.jsf.FacesUtil;

import lombok.Getter;
import lombok.Setter;

@ViewScoped
@Named("pesquisaUsuario")
public class PesquisaUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioService usuarioService;

	@Getter
	private List<Usuario> usuarios;

	@Getter
	@Setter
	private Usuario usuarioSelecionado;

	@Getter
	@Setter
	private String nome;

	@PostConstruct
	public void inicializar() {
		usuarios = usuarioService.buscarTodos();
	}

	public void excluir() {
		try {
			usuarioService.excluir(usuarioSelecionado);
			usuarios.remove(usuarioSelecionado);
			FacesUtil.addSuccessMessage("Usuário " + usuarioSelecionado.getUsername() + " excluído com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public void filtrar() {
		usuarios = usuarioService.buscarPorNome(nome);
	}

}
