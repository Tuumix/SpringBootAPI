package com.API.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.API.api.model.Usuarios;
import com.API.api.services.UsuarioService;

import dtos.LoginInfoDTO;

@RestController
public class AutenticacaoController {

	@Autowired
	UsuarioService usuarioService;	
	
	@PostMapping("/login")
	ResponseEntity<Usuarios> login(@RequestBody LoginInfoDTO login) {
		Usuarios user = usuarioService.autenticar(login.getLogin(), login.getSenha());
		if( user != null && user.getStatus() == 'A'){
			return new ResponseEntity<Usuarios>(user, HttpStatus.OK);
		} else
			return new ResponseEntity<Usuarios>(user, HttpStatus.BAD_REQUEST);
	}
}
