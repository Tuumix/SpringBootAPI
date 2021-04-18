package com.API.api.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.API.api.model.Usuarios;

public interface UsuarioService extends CrudRepository<Usuarios, Integer>{
	//query to authenticate
	@Query(value = "select * from usuarios where login = ?1 and senha = ?2", nativeQuery = true)
	Usuarios autenticar(String login, String senha);
	
	//query to search if there is the same login
	@Query(value = "select * from usuarios where login = ?1", nativeQuery = true)
	Usuarios getUserByLogin(String login);
	
	@Query(value = "select * from usuarios where nome = ?1 or email = ?2", nativeQuery = true)
	List<Usuarios> getUserByNameEmail(String nome, String email);
	
	@Query(value = "select * from usuarios where nome = ?1 and email = ?2", nativeQuery = true)
	List<Usuarios> getByAnd(String nome, String email);
}
