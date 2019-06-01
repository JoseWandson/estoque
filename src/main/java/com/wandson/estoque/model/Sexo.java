package com.wandson.estoque.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Sexo {

	M("Masculino"), F("Feminino");

	@Getter
	private String descricao;

}
