package com.tty2000.cliente.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Cidade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idCidade;

	@NotBlank
	@Column
	private String nome;

	@ManyToOne
	@JoinColumn(name = "id_estado", nullable = true)
	private Estado estado;

	public Cidade() {

	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Cidade(String nomeCidade) {
		this.nome = nomeCidade;
		;
	}

	public long getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(long idCidade) {
		this.idCidade = idCidade;
	}

	public String getNomeCidade() {
		return nome;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nome = nomeCidade;
	}

	@Override
	public String toString() {
		return "Cidade [id=" + idCidade + ", Nome=" + nome + "]";
	}

}
