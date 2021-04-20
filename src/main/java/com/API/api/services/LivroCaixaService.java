package com.API.api.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.API.api.model.LivroCaixa;

public interface LivroCaixaService extends CrudRepository<LivroCaixa, Integer>{
	@Query(value = "select * from livro_caixa where id_cliente = ?1", nativeQuery = true)
	List<LivroCaixa> getByClienteId(int id);
}
