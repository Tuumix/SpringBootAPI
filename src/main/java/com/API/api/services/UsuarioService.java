package com.API.api.services;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.API.api.model.Usuarios;

public interface UsuarioService extends CrudRepository<Usuarios, Integer>{
	
	Usuarios findByLoginAndSenha(String login, String senha);
	
	Usuarios findByLogin(String login);
	
	List<Usuarios> findByNomeOrEmail(String nome, String email);
	
	List<Usuarios> findByNomeAndEmail(String nome, String email);

}
