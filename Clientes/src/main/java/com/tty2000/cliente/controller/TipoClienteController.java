package com.tty2000.cliente.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tty2000.cliente.enums.EnTipoCliente;
import com.tty2000.cliente.exception.ResourceNotFoundException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TipoClienteController {

	@GetMapping("/tiposCliente")
	public ResponseEntity<List<String>> listar() {
		List<String> tipo = EnTipoCliente.getListDescricaoTipoCliente();
		return ResponseEntity.ok().body(tipo);
	}

	@GetMapping("/tipo/{id}")
	public ResponseEntity<String> getTipoPorId(@PathVariable(value = "id") Integer idTipo)
			throws ResourceNotFoundException {
		String tipo = EnTipoCliente.getDescricao(idTipo);
		return ResponseEntity.ok().body(tipo);
	}

}
