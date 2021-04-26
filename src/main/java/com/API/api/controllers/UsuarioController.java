package com.API.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.API.api.model.Usuarios;
import com.API.api.services.UsuarioService;

import dtos.SearchDTO;

@RestController
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@PostMapping("/usuarios")
	ResponseEntity<Usuarios> save(@RequestBody Usuarios user) {
		Usuarios aux = usuarioService.findByLogin(user.getLogin());
		if(aux == null) {
			usuarioService.save(user);
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/usuarios")
	ResponseEntity<Iterable<Usuarios>> getUsuarios() {
		return ResponseEntity.ok(usuarioService.findAll());
	}
	
	@GetMapping("/usuarios/{id}")
	Usuarios get(@PathVariable int id){
		return usuarioService.findById(id).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
	}
	
	@DeleteMapping("/usuarios/{id}")
	void delete(@PathVariable int id) {
		usuarioService.deleteById(id);
	}
	
	@PutMapping("/usuarios")
	ResponseEntity<Usuarios> update(@RequestBody Usuarios user) {
		return ResponseEntity.ok(usuarioService.save(user));
	}
	
	@GetMapping("/usuarios/busca")
	Iterable<Usuarios> getByNameEmail(@RequestBody SearchDTO search) {
		if(!search.getNome().isBlank() && !search.getEmail().isBlank())
			return usuarioService.findByNomeAndEmail(search.getNome(), search.getEmail());
		else
			return usuarioService.findByNomeOrEmail(search.getNome(), search.getEmail());
	}
}
