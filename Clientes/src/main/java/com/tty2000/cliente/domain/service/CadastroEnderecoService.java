package com.tty2000.cliente.domain.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.tty2000.cliente.exception.ResourceNotFoundException;
import com.tty2000.cliente.model.Endereco;
import com.tty2000.cliente.repository.EnderecoRepository;

@Service
public class CadastroEnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	public Endereco salvar(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}

	public Map<String, Boolean> deleteEndereco(@PathVariable(value = "id") Long idEndereco)
			throws ResourceNotFoundException {
		Endereco endereco = enderecoRepository.findById(idEndereco).orElseThrow(
				() -> new ResourceNotFoundException("Endereço não encontrado com este id :: " + idEndereco));

		enderecoRepository.delete(endereco);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
