package com.wandson.estoque.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(of = "id")
public class Municipio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@NotBlank(message = "Nome não pode estar em branco")
	private String nome;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_estado", nullable = false)
	private Estado estado;

	@OneToMany(mappedBy = "municipio", cascade = CascadeType.REMOVE)
	private List<Endereco> enderecos;

	public Municipio() {
		estado = new Estado();
		enderecos = new ArrayList<>();
	}

}
