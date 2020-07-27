package com.tty2000.cliente.domain.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.tty2000.cliente.exception.ResourceNotFoundException;
import com.tty2000.cliente.model.Cliente;
import com.tty2000.cliente.model.Endereco;
import com.tty2000.cliente.repository.ClienteRepository;

@Service
public class CadastroClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente salvar(Cliente cliente) {

		// regra cpf e cnpf únicos
		Cliente clienteExistente = clienteRepository.findByCpfCnpj(cliente.getCpfCnpj());
		if (clienteExistente != null && !clienteExistente.equals(cliente)) {
			if (clienteExistente.getCpfCnpj().length() == 14) {
				throw new ResourceNotFoundException("Já esixte um cliente cadastrado com este CNPJ");
			} else if (clienteExistente.getCpfCnpj().length() == 11) {
				throw new ResourceNotFoundException("Já esixte um cliente cadastrado com este CPF");
			}

		}
		// regra data de nascimento menor que a data atual
		System.out.print(new Date());
		if (cliente.getDtNascimento().equals(new Date())) {
			throw new ResourceNotFoundException("A data de nascimento deve ser menor que a data atual" + new Date());
		}
		
		if(cliente.getEndereco( )== null) {
			Endereco endereco = new Endereco();
			endereco.setIdEndereco(1L);
			cliente.setEndereco(endereco);
		}

		return clienteRepository.save(cliente);
	}

	public Map<String, Boolean> deleteCliente(@PathVariable(value = "id") Long IdCliente)
			throws ResourceNotFoundException {
		Cliente cliente = clienteRepository.findById(IdCliente)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com este id :: " + IdCliente));

		clienteRepository.delete(cliente);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
