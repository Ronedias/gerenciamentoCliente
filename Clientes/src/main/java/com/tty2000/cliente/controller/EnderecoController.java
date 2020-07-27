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

import com.tty2000.cliente.domain.service.CadastroEnderecoService;
import com.tty2000.cliente.exception.ResourceNotFoundException;
import com.tty2000.cliente.model.Endereco;
import com.tty2000.cliente.repository.EnderecoRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EnderecoController {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private CadastroEnderecoService cadastroEnderecoService;

	@GetMapping("/enderecos")
	public List<Endereco> listar() {
		return enderecoRepository.findAll();
	}

	@GetMapping("/enderecos/{id}")
	public ResponseEntity<Endereco> getEnderecoPorId(@PathVariable(value = "id") Long idEndereco)
			throws ResourceNotFoundException {
		Endereco endereco = enderecoRepository.findById(idEndereco).orElseThrow(
				() -> new ResourceNotFoundException("Endereço não encontrado com este id :: " + idEndereco));
		return ResponseEntity.ok().body(endereco);
	}

	@PostMapping("/enderecos")
	@ResponseStatus(HttpStatus.CREATED)
	public Endereco createEndereco(@Valid @RequestBody Endereco endereco) {
		return cadastroEnderecoService.salvar(endereco);
	}

	@PutMapping("/enderecos/{id}")
	public ResponseEntity<Endereco> updateEndereco(@PathVariable(value = "id") Long idEndereco,

			@Valid @RequestBody Endereco enderecoDetails) throws ResourceNotFoundException {
		Endereco endereco = enderecoRepository.findById(idEndereco).orElseThrow(
				() -> new ResourceNotFoundException("Endereço não encontrado com este id :: " + idEndereco));

		endereco.setBairro(enderecoDetails.getBairro());
		endereco.setCep(enderecoDetails.getCep());
		endereco.setCidade(enderecoDetails.getCidade());
		endereco.setComplemento(enderecoDetails.getComplemento());
		endereco.setLogradouro(enderecoDetails.getLogradouro());

		final Endereco updatedEndereco = enderecoRepository.save(endereco);
		return ResponseEntity.ok(updatedEndereco);
	}

	@DeleteMapping("/enderecos/{id}")
	public Map<String, Boolean> deleteEndereco(@PathVariable(value = "id") Long idEndereco)
			throws ResourceNotFoundException {

		cadastroEnderecoService.deleteEndereco(idEndereco);

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
