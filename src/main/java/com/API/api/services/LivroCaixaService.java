package com.API.api.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RestController;

import com.API.api.model.LivroCaixa;


@RestController
public interface LivroCaixaService extends CrudRepository<LivroCaixa, Integer>{
	@Query(value = "select * from livro_caixa where id_cliente = ?1", nativeQuery = true)
	List<LivroCaixa> getByClienteId(int id);
	
	@Query(value = "select * from livro_caixa where id_cliente = ?1 and datalancamento between ?2 and ?3", nativeQuery = true)
	List<LivroCaixa> getRelatorio(int id, Calendar data1, Calendar data2);
}
