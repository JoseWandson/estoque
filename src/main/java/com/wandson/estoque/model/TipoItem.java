package com.wandson.estoque.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TipoItem {

	ALIMENTO("Alimento"), ELETRODOMESTICO("Eletrodoméstico"), ELETROPORTATEIS("Eletroportáteis"),
	INFORMATICA("Informática");

	@Getter
	private String descricao;

}
