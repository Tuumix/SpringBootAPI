package com.API.api.services;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.API.api.model.Usuarios;

public interface AutenticarService extends CrudRepository<Usuarios, Integer>{
	@Query(value = "select * from usuarios where login = ?1 and senha = ?2", nativeQuery = true)
	Usuarios autenticar(String login, String senha);
}
