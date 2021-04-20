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

import com.API.api.model.LivroCaixa;
import com.API.api.services.ClienteService;
import com.API.api.services.LivroCaixaService;

import dtos.ContabilDTO;

@RestController
public class LivroCaixaController {
	
	@Autowired
	private LivroCaixaService livroCaixaService;
	
	@Autowired
	private ClienteService clientesService;
	
	@GetMapping("/livroscaixa/{id}")
	ResponseEntity<LivroCaixa> getById(@PathVariable int id) {
		Optional<LivroCaixa> aux = livroCaixaService.findById(id);
		if(aux.isPresent())
			return new ResponseEntity<LivroCaixa>(livroCaixaService.findById(id).get(), HttpStatus.OK);
		return new ResponseEntity<LivroCaixa>(livroCaixaService.findById(id).get(), HttpStatus.OK);
	}
	
	@PostMapping("/livroscaixa")
	ResponseEntity<LivroCaixa> save(@RequestBody LivroCaixa livrosCaixa) {
		return new ResponseEntity<LivroCaixa>(livroCaixaService.save(livrosCaixa), HttpStatus.OK);
	}
	
	@DeleteMapping("/livroscaixa/{id}")
	ResponseEntity<?> delete(@PathVariable int id) {
		livroCaixaService.deleteById(id);
		return new ResponseEntity<String>("Removido com sucesso!", HttpStatus.OK);
	}
	
	@GetMapping("/livroscaixa/cliente/{id}")
	List<LivroCaixa> getByClienteId(@PathVariable int id) {
		return livroCaixaService.getByClienteId(id);
	}
	
	@PutMapping("/livroscaixa")
	ResponseEntity<LivroCaixa> update(@RequestBody LivroCaixa livroCaixa) {
		return new ResponseEntity<LivroCaixa>(livroCaixaService.save(livroCaixa), HttpStatus.OK);
	}
	
	//Last part
//	@GetMapping("/livroscaixa/relatorio/{id}")
//	ResponseEntity<ContabilDTO> getRelatorio(@PathVariable int id) {
//		Optional<Clientes> cli = clientesService.findById(id);
//		
////		ContabilDTO relatorio = new ContabilDTO()
//		List<LivroCaixa> aux = livroCaixaService.getByClienteId(id);
//	}
}
