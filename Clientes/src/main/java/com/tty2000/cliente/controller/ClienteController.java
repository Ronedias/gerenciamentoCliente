
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

import com.tty2000.cliente.domain.service.CadastroClienteService;
import com.tty2000.cliente.enums.EnTipoCliente;
import com.tty2000.cliente.exception.ResourceNotFoundException;
import com.tty2000.cliente.model.Cliente;
import com.tty2000.cliente.repository.ClienteRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private CadastroClienteService cadastroClienteService;

	@GetMapping("/clientes")
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}

	@GetMapping("/clientes/{id}")
	public ResponseEntity<Cliente> getClientePorId(@PathVariable(value = "id") Long clienteId)
			throws ResourceNotFoundException {
		Cliente cliente = clienteRepository.findById(clienteId)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com este id :: " + clienteId));
		return ResponseEntity.ok().body(cliente);
	}

	@PostMapping("/clientes")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente createCliente(@Valid @RequestBody Cliente cliente) {
		return cadastroClienteService.salvar(cliente);
	}

	@PutMapping("/clientes/{id}")
	public ResponseEntity<Cliente> updateCliente(@PathVariable(value = "id") Long clienteId,

			@Valid @RequestBody Cliente clienteDetails) throws ResourceNotFoundException {
		Cliente cliente = clienteRepository.findById(clienteId)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com este id :: " + clienteId));

		cliente.setEmail(clienteDetails.getEmail());
		cliente.setNome(clienteDetails.getNome());
		cliente.setCpfCnpj(clienteDetails.getCpfCnpj());
		cliente.setDtNascimento(clienteDetails.getDtNascimento());
		cliente.setTipoCliente(EnTipoCliente.getEnTipoCliente(clienteDetails.getTipoCliente().getCodigo()));
		final Cliente updatedCliente = clienteRepository.save(cliente);
		return ResponseEntity.ok(updatedCliente);
	}

	@DeleteMapping("/clientes/{id}")
	public Map<String, Boolean> deleteCliente(@PathVariable(value = "id") Long IdCliente)
			throws ResourceNotFoundException {

		cadastroClienteService.deleteCliente(IdCliente);

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
