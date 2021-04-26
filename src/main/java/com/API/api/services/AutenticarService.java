package com.API.api.services;

import org.springframework.data.repository.CrudRepository;

import com.API.api.model.Usuarios;

public interface AutenticarService extends CrudRepository<Usuarios, Integer>{
	Usuarios findByLoginAndSenha(String login, String senha);
}
