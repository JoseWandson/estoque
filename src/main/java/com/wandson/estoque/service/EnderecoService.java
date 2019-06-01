package com.wandson.estoque.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import com.mysql.cj.util.StringUtils;
import com.wandson.estoque.dao.EnderecoDAO;
import com.wandson.estoque.model.Endereco;
import com.wandson.estoque.util.jpa.Transactional;

public class EnderecoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EnderecoDAO enderecoDAO;

	public Endereco buscarPeloId(Long id) {
		return enderecoDAO.buscarPeloId(id);
	}

	@Transactional
	public void salvar(Endereco endereco) throws NegocioException {
		enderecoDAO.salvar(endereco);
	}

	public List<Endereco> buscarTodos() {
		return enderecoDAO.buscarTodos();
	}

	public List<Endereco> buscarPorCep(String cep) {
		if (StringUtils.isEmptyOrWhitespaceOnly(cep)) {
			return buscarTodos();
		}
		return enderecoDAO.buscarPorCep(cep);
	}

	@Transactional
	public void excluir(Endereco endereco) throws NegocioException {
		try {
			enderecoDAO.excluir(endereco);
		} catch (PersistenceException e) {
			throw new NegocioException("Endereco não pode ser excluído.");
		}
	}

	public Boolean existe(Endereco endereco) {
		return enderecoDAO.existe(endereco);
	}

}
