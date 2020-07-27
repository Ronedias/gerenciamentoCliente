package com.tty2000.cliente.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Estado {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idEstado;

	@NotBlank
	@Column
	private String nome;

	public Estado() {

	}

	public Estado(String nomeEstado) {
		this.nome = nomeEstado;
		;
	}

	public long getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(long idEstado) {
		this.idEstado = idEstado;
	}

	public String getNomeEstado() {
		return nome;
	}

	public void setNomeEstado(String nomeEstado) {
		this.nome = nomeEstado;
	}

	@Override
	public String toString() {
		return "Cidade [id=" + idEstado + ", Nome=" + nome + "]";
	}

}
