package com.tty2000.cliente.domain.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.tty2000.cliente.exception.ResourceNotFoundException;
import com.tty2000.cliente.model.Estado;
import com.tty2000.cliente.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

	@Autowired
	private EstadoRepository estadoRepository;

	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}

	public Map<String, Boolean> deleteEstado(@PathVariable(value = "id") Long IdEstado)
			throws ResourceNotFoundException {
		Estado estado = estadoRepository.findById(IdEstado)
				.orElseThrow(() -> new ResourceNotFoundException("Estado não encontrado com este id :: " + IdEstado));

		estadoRepository.delete(estado);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
