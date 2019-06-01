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
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(nullable = false, length = 8)
	private String cep;

	@NotBlank
	@Column(nullable = false)
	private String logradouro;

	@NotNull
	@Column(nullable = false)
	private Integer numero;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_municipio", nullable = false)
	private Municipio municipio;

	@Transient
	@OneToMany(mappedBy = "endereco", cascade = CascadeType.REMOVE)
	private List<Usuario> usuarios;

	public Endereco() {
		municipio = new Municipio();
		usuarios = new ArrayList<>();
	}

}
