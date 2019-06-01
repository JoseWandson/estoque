package com.wandson.estoque.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mysql.cj.util.StringUtils;
import com.wandson.estoque.model.Endereco;
import com.wandson.estoque.model.Estado;
import com.wandson.estoque.model.Municipio;
import com.wandson.estoque.model.Sexo;
import com.wandson.estoque.model.TipoUsuario;
import com.wandson.estoque.model.Usuario;
import com.wandson.estoque.service.EnderecoService;
import com.wandson.estoque.service.EstadoService;
import com.wandson.estoque.service.MunicipioService;
import com.wandson.estoque.service.NegocioException;
import com.wandson.estoque.service.UsuarioService;
import com.wandson.estoque.util.jsf.FacesUtil;

import lombok.Getter;
import lombok.Setter;

@ViewScoped
@Named("cadastroUsuario")
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private Usuario usuario;

	@Getter
	private Boolean desabilitaEndereco;

	@Getter
	private List<Estado> estados;

	@Getter
	private List<Municipio> municipios;

	@Inject
	private UsuarioService usuarioService;

	@Inject
	private EnderecoService enderecoService;

	@Inject
	private EstadoService estadoService;

	@Inject
	private MunicipioService municipioService;

	@PostConstruct
	public void inicializar() {
		limpar();
		desabilitaEndereco = true;
		estados = estadoService.buscarTodos();
	}

	public void salvar() {
		try {
			usuarioService.salvar(usuario);
			FacesUtil.addSuccessMessage("Usuário salvo com sucesso!");
			if (Objects.isNull(usuario.getId())) {
				limpar();
			}
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public void carregarEndereco() {
		if (!StringUtils.isEmptyOrWhitespaceOnly(usuario.getEndereco().getCep())
				&& usuario.getEndereco().getCep().length() == 8) {
			List<Endereco> enderecos = enderecoService.buscarPorCep(usuario.getEndereco().getCep());
			Optional<Endereco> endereco = enderecos.stream().findFirst();
			desabilitaEndereco = false;
			if (endereco.isPresent()) {
				usuario.setEndereco(endereco.get());
				desabilitaEndereco = true;
				carregarMunicipios();
			}
		}
	}

	public void carregarMunicipios() {
		municipios = municipioService.buscarPorEstado(usuario.getEndereco().getMunicipio().getEstado());
	}

	public Sexo[] getSexos() {
		return Sexo.values();
	}

	public TipoUsuario[] getTipos() {
		return TipoUsuario.values();
	}

	private void limpar() {
		usuario = new Usuario();

	}

}
