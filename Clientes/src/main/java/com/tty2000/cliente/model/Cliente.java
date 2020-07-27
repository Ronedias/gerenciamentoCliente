package com.tty2000.cliente.model;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tty2000.cliente.enums.EnTipoCliente;

@Entity
public class Cliente {

	@Id
	@Column(name = "id_cliente", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotBlank
	private String nome;

	@NotBlank
	@Size(min = 11, max = 14)
	@Column(name = "doc_identificacao", nullable = false)
	private String cpfCnpj;

	@NotBlank
	@Email
	private String email;

	@NotNull
	private EnTipoCliente tipoCliente;

	@NotNull
	private Date dtNascimento;

//	@Column
//	private LocalDateTime dtRegistro;

	@ManyToOne
	@JoinColumn(name = "id_endereco", nullable = true)
	private Endereco endereco;

	public Cliente() {

	}

	public Cliente(String nome, String cpfCnpj, String email, EnTipoCliente tipoCliente, Date dataNascimento,
			LocalDateTime dtRegistro, Endereco endereco) {
		this.nome = nome;
		this.cpfCnpj = cpfCnpj;
		this.email = email;
		this.tipoCliente = tipoCliente;
		this.dtNascimento = dataNascimento;
		this.endereco = endereco;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	@Column(name = "email", nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String emailId) {
		this.email = emailId;
	}

	public EnTipoCliente getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(EnTipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", Nome=" + nome + ", Docu,entoIdentificacao=" + cpfCnpj + ", email=" + email
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
