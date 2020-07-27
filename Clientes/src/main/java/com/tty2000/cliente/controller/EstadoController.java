package com.tty2000.cliente.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tty2000.cliente.domain.service.CadastroEstadoService;
import com.tty2000.cliente.exception.ResourceNotFoundException;
import com.tty2000.cliente.model.Estado;
import com.tty2000.cliente.repository.EstadoRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CadastroEstadoService cadastroEstadoService;

	@GetMapping("/estados")
	public List<Estado> listar() {
		return estadoRepository.findAll();
	}

	@GetMapping("/estados/{id}")
	public ResponseEntity<Estado> getEstadoPorId(@PathVariable(value = "id") Long idEstado)
			throws ResourceNotFoundException {
		Estado estado = estadoRepository.findById(idEstado)
				.orElseThrow(() -> new ResourceNotFoundException("Estado não encontrado com este id :: " + idEstado));
		return ResponseEntity.ok().body(estado);
	}

	@PostMapping("/estados")
	@ResponseStatus(HttpStatus.CREATED)
	public Estado createEstado(@Valid @RequestBody Estado estado) {
		return cadastroEstadoService.salvar(estado);
	}

	@PutMapping("/estados/{id}")
	public ResponseEntity<Estado> updateEstado(@PathVariable(value = "id") Long idEstado,

			@Valid @RequestBody Estado estadoDetails) throws ResourceNotFoundException {
		Estado estado = estadoRepository.findById(idEstado)
				.orElseThrow(() -> new ResourceNotFoundException("Estado não encontrado com este id :: " + idEstado));

		estado.setNomeEstado(estadoDetails.getNomeEstado());

		final Estado updatedEstado = estadoRepository.save(estado);
		return ResponseEntity.ok(updatedEstado);
	}

	@DeleteMapping("/estados/{id}")
	public Map<String, Boolean> deleteCliente(@PathVariable(value = "id") Long idEstado)
			throws ResourceNotFoundException {

		cadastroEstadoService.deleteEstado(idEstado);

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
