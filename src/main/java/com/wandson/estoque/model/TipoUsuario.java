package com.wandson.estoque.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TipoUsuario {

	PF("Pessoa Física"), PJ("Pessoa Jurídica");

	@Getter
	private String descricao;

}
