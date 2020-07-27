package com.tty2000.cliente.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tty2000.cliente.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Cliente findByCpfCnpj(String cpfCnpj);

	Cliente findByDtNascimento(Date dtNascimento);

}
