package com.API.api.controllers;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

import com.API.api.model.LivroCaixa;
import com.API.api.services.LivroCaixaService;

import dtos.Contabil;
import dtos.RelatorioDTO;
import dtos.ContabilRequestDTO;
import enums.Tipo;

@RestController
public class LivroCaixaController {
	
	@Autowired
	private LivroCaixaService livroCaixaService;
	
	@GetMapping("/livroscaixa/{id}")
	ResponseEntity<?> getById(@PathVariable int id) {
		Optional<LivroCaixa> aux = livroCaixaService.findById(id);
		if(aux.isPresent())
			return new ResponseEntity<LivroCaixa>(livroCaixaService.findById(id).get(), HttpStatus.OK);
		return new ResponseEntity<String>("Não encontrado", HttpStatus.OK);
	}
	
	@PostMapping("/livroscaixa")
	ResponseEntity<LivroCaixa> save(@RequestBody LivroCaixa livrosCaixa) {
		return new ResponseEntity<LivroCaixa>(livroCaixaService.save(livrosCaixa), HttpStatus.OK);
	}
	
	@DeleteMapping("/livroscaixa/{id}")
	ResponseEntity<?> delete(@PathVariable int id) {
		livroCaixaService.deleteById(id);
		return new ResponseEntity<String>("Removido com sucesso!", HttpStatus.OK);
	}
	
	@GetMapping("/livroscaixa/cliente/{id}")
	ResponseEntity<?> getByClienteId(@PathVariable int id) {
		List<LivroCaixa> list = livroCaixaService.getByClienteId(id);

		if(list != null) {
			return new ResponseEntity<List<LivroCaixa>>(list, HttpStatus.OK);
		}
		return new ResponseEntity<List<LivroCaixa>>(list, HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/livroscaixa")
	ResponseEntity<LivroCaixa> update(@RequestBody LivroCaixa livroCaixa) {
		return new ResponseEntity<LivroCaixa>(livroCaixaService.save(livroCaixa), HttpStatus.OK);
	}
	
	@GetMapping("/livroscaixa/relatorio")
	ResponseEntity<RelatorioDTO> getRelatorio(@RequestBody ContabilRequestDTO req) {
		List<LivroCaixa> lista = livroCaixaService.getRelatorio(req.getId(), req.getDataInicial(), req.getDataFinal());
		if(lista.size() > 0) {
			double valor = 0;
			List<Contabil> contab = new ArrayList<Contabil>();
			RelatorioDTO relatorio = new RelatorioDTO(
					lista.get(0).getId_cliente().getId(),
					lista.get(0).getId_cliente().getNome(),
					lista.get(0).getId_cliente().getCpfCnpj(),
					lista.get(0).getId_cliente().getTelefone(),
					contab
			);
			
			for(LivroCaixa item: lista) {
				valor = item.getTipo() == Tipo.C ? valor + item.getValor() : valor - item.getValor();
				contab.add(new Contabil(item.getDataLancamento(), item.getDescricao(), item.getTipo(), item.getValor(), valor));
			}
			return new ResponseEntity<RelatorioDTO>(relatorio, HttpStatus.OK);
		}
			return new ResponseEntity<RelatorioDTO>(HttpStatus.OK);
	}
}
