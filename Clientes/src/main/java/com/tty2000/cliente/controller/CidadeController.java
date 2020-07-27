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

import com.tty2000.cliente.domain.service.CadastroCidadeService;
import com.tty2000.cliente.exception.ResourceNotFoundException;
import com.tty2000.cliente.model.Cidade;
import com.tty2000.cliente.repository.CidadeRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CadastroCidadeService cadastroCidadeService;

	@GetMapping("/cidades")
	public List<Cidade> listar() {
		return cidadeRepository.findAll();
	}

	@GetMapping("/cidades/{id}")
	public ResponseEntity<Cidade> getCidadePorId(@PathVariable(value = "id") Long idCidade)
			throws ResourceNotFoundException {
		Cidade cidade = cidadeRepository.findById(idCidade)
				.orElseThrow(() -> new ResourceNotFoundException("Cidade não encontrado com este id :: " + idCidade));
		return ResponseEntity.ok().body(cidade);
	}

	@PostMapping("/cidades")
	@ResponseStatus(HttpStatus.CREATED)
	public Cidade createCidade(@Valid @RequestBody Cidade cidade) {
		return cadastroCidadeService.salvar(cidade);
	}

	@PutMapping("/cidades/{id}")
	public ResponseEntity<Cidade> updateCidade(@PathVariable(value = "id") Long idCidade,

			@Valid @RequestBody Cidade cidadeDetails) throws ResourceNotFoundException {
		Cidade cidade = cidadeRepository.findById(idCidade)
				.orElseThrow(() -> new ResourceNotFoundException("Cidade não encontrado com este id :: " + idCidade));

		cidade.setNomeCidade(cidadeDetails.getNomeCidade());
		cidade.setEstado(cidadeDetails.getEstado());

		final Cidade updatedCidade = cidadeRepository.save(cidade);
		return ResponseEntity.ok(updatedCidade);
	}

	@DeleteMapping("/cidades/{id}")
	public Map<String, Boolean> deleteCliente(@PathVariable(value = "id") Long idCidade)
			throws ResourceNotFoundException {

		cadastroCidadeService.deleteCidade(idCidade);

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
