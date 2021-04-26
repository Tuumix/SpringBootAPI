package com.API.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.API.api.model.Usuarios;
import com.API.api.services.AutenticarService;

import dtos.LoginInfoDTO;
import enums.Status;

@RestController
public class AutenticacaoController {

	@Autowired
	AutenticarService autenticarService;	
	
	@PostMapping("/login")
	ResponseEntity<String> login(@RequestBody LoginInfoDTO login) {
		Usuarios user = autenticarService.findByLoginAndSenha(login.getLogin(), login.getSenha());
		if(user != null && user.getStatus() != Status.C)  {
			return new ResponseEntity<String>("Logado com sucesso!", HttpStatus.OK);
		} else
			return new ResponseEntity<String>("Usuário cancelado || Senha incorreta", HttpStatus.BAD_REQUEST);
	}
}
