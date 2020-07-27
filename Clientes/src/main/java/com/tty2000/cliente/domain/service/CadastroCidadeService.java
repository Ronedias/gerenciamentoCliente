package com.tty2000.cliente.domain.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.tty2000.cliente.exception.ResourceNotFoundException;
import com.tty2000.cliente.model.Cidade;
import com.tty2000.cliente.repository.CidadeRepository;

@Service
public class CadastroCidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;

	public Cidade salvar(Cidade cidade) {
		return cidadeRepository.save(cidade);
	}

	public Map<String, Boolean> deleteCidade(@PathVariable(value = "id") Long idCidade)
			throws ResourceNotFoundException {
		Cidade cidade = cidadeRepository.findById(idCidade)
				.orElseThrow(() -> new ResourceNotFoundException("Cidade não encontrado com este id :: " + idCidade));

		cidadeRepository.delete(cidade);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
