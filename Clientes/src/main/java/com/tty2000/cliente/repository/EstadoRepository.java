package com.tty2000.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tty2000.cliente.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

}
