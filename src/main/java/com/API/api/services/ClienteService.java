package com.API.api.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.API.api.model.Clientes;

public interface ClienteService extends CrudRepository<Clientes, Integer>{

	//Is this fucking correct? maybe but probably not
	@Query(value = "select * from clientes where nome = ?1 or cpfcnpj = ?2 or cidade = ?3 or uf = ?4", nativeQuery = true)
	List<Clientes> getData(String nome, String cpfCnpj, String cidade, String uf);
	
}
