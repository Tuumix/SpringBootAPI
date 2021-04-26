package com.API.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.API.api.model.Clientes;
import com.API.api.services.ClienteService;

import dtos.ClienteFiltroDTO;

@RestController
public class ClientesController {
	
	@Autowired
	private ClienteService clienteService;
	
	@PostMapping("/clientes")
	ResponseEntity<Clientes> save(@RequestBody Clientes cli) {
		Clientes aux = clienteService.save(cli);
		return new ResponseEntity<Clientes>(aux, HttpStatus.OK);
	}
	
	@GetMapping("/clientes/{id}")
	ResponseEntity<?> getAll(@PathVariable int id) {
		Optional<Clientes> cliente = clienteService.findById(id);
		if(cliente.isPresent()) {
			return new ResponseEntity<Clientes>(cliente.get(), HttpStatus.OK);
		}
		return new ResponseEntity<String>("Cliente não encontrado!", HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/clientes/{id}")
	void delete(@PathVariable int id) {
		clienteService.deleteById(id);
	}
	
	@PutMapping("/clientes")
	ResponseEntity<Clientes> update(@RequestBody Clientes cli) {
		return new ResponseEntity<Clientes>(clienteService.save(cli), HttpStatus.OK);
	}
	
	@GetMapping("/clientes/search")
	List<Clientes> getAll(@RequestBody ClienteFiltroDTO filtro) {
		if(!filtro.getNome().isBlank() && !filtro.getCpfCnpj().isBlank() && !filtro.getCidade().isBlank() && !filtro.getUf().isBlank())
			return clienteService.getFilterByAnd(filtro.getNome(), filtro.getCpfCnpj(), filtro.getCidade(), filtro.getUf());
		return clienteService.getFilter(filtro.getNome(), filtro.getCpfCnpj(), filtro.getCidade(), filtro.getUf());
	}
}
