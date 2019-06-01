package com.wandson.estoque.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;

@Data
@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Length(min = 31)
	@Column(nullable = false)
	private String nome;

	@NotBlank
	@Column(nullable = false, unique = true)
	private String username;

	@NotBlank
	@Length(min = 8)
	@Column(nullable = false)
	private String password;

	@NotNull
	@JoinColumn(name = "id_endereco", nullable = false)
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Endereco endereco;

	@Email
	@NotBlank
	@Column(nullable = false, unique = true)
	private String email;

	@NotNull
	@Past
	@Column(nullable = false)
	private LocalDate dataDeNascimento;

	@NotNull
	@Column(nullable = false, length = 1)
	@Enumerated(EnumType.STRING)
	private Sexo sexo;

	@NotNull
	@Column(nullable = false, length = 2)
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipo;

	@CPF
	@Length(max = 14, min = 14)
	@Column(unique = true, length = 14)
	private String cpf;

	@CNPJ
	@Length(max = 18, min = 18)
	@Column(length = 18)
	private String cnpj;

	public Usuario() {
		endereco = new Endereco();
	}

	public Boolean getPessoaFisica() {
		return TipoUsuario.PF.equals(tipo);
	}

}
