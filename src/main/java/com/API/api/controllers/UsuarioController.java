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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.API.api.model.Usuarios;
import com.API.api.services.UsuarioService;

import dtos.SearchDTO;

@RestController
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	//Insert done!
	@PostMapping("/usuarios")
	ResponseEntity<Usuarios> save (@RequestBody Usuarios user) {
		Usuarios aux = usuarioService.getUserByLogin(user.getLogin());
		if(aux != null) {
			return new ResponseEntity<Usuarios>(aux, HttpStatus.BAD_REQUEST);
		} else
			return new ResponseEntity<Usuarios>(usuarioService.save(user), HttpStatus.OK);
	}
	
	//Get all the users done!
	@GetMapping("/usuarios")
	Iterable<Usuarios> getUsuarios() {
		return usuarioService.findAll();
	}
	
	//It's working
	@GetMapping("/usuarios/{id}")
	Optional<Usuarios>get(@PathVariable int id){
		return usuarioService.findById(id);
	}
	
	//Deleted done!
	@DeleteMapping("/usuarios/{id}")
	void delete(@PathVariable int id) {
		usuarioService.deleteById(id);
	}
	
	//Update working
	@PutMapping("/usuarios")
	ResponseEntity<Usuarios> update(@RequestBody Usuarios user) {
		if(usuarioService.findById(user.getId()).isPresent()) {
			System.out.println("Entrando nesse trem");
			return new ResponseEntity<Usuarios>(usuarioService.save(user), HttpStatus.OK);
		}
		return new ResponseEntity<Usuarios>(user, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/usuarios/busca")
	Iterable<Usuarios> getByNameEmail(@RequestBody SearchDTO search) {
		if(!search.getNome().isBlank() && !search.getEmail().isBlank()) {
			List<Usuarios> aux = usuarioService.getByAnd(search.getNome(), search.getEmail());
			return aux;
		}
		List<Usuarios> aux = usuarioService.getUserByNameEmail(search.getNome(), search.getEmail());
		return aux;
	}
}
