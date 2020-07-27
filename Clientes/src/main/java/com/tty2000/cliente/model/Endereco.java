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
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idEndereco;

	@NotBlank
	@Column
	private String logradouro;

	@Column
	private String complemento;

	@NotBlank
	@Column
	private String numero;

	@NotBlank
	@Column
	private String bairro;

	@NotBlank
	@Column
	private String cep;

	@ManyToOne
	@JoinColumn(name = "id_cidade", nullable = true)
	private Cidade cidade;

	public Endereco() {

	}

	public Endereco(String logradouro, String complemento, String numero, String bairro, String cep, Cidade cidade) {
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.numero = numero;
		this.bairro = bairro;
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public long getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(long idEndereco) {
		this.idEndereco = idEndereco;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	@Override
	public String toString() {
		return "Endereco [id=" + idEndereco + ", logradouro=" + logradouro + ", complemento=" + complemento
				+ ", numero=" + numero + " + bairro=" + bairro + ", cep=" + cep + "]";
	}

}
