package com.API.api.controllers;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.API.api.model.Usuarios;
import com.API.api.services.UsuarioService;

import dtos.SearchDTO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;

@RestController
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@PostMapping("/usuarios")
	ResponseEntity<Usuarios> save(@RequestBody Usuarios user) {
		Usuarios aux = usuarioService.findByLogin(user.getLogin());
		if(aux == null) {
			usuarioService.save(user);
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/usuarios")
	String getUsuarios(@RequestParam String tipo) throws FileNotFoundException, JRException{
		List<Usuarios> list = (List<Usuarios>) usuarioService.findAll();
		File file = ResourceUtils.getFile("classpath:usuario.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
		Map<String, Object>  map = new HashMap<>();
		map.put("Creted by", "Lindo");
		JasperPrint jasperPrint  = JasperFillManager.fillReport(jasperReport, map, dataSource);
		
		if(tipo.equals("html"))
			JasperExportManager.exportReportToHtmlFile(jasperPrint, "C:\\" + "usuariosHTML.html");
		else
			JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\" + "usuariosRelatorio.pdf");

		return "";
	}
	
	@GetMapping("/usuarios/{id}")
	Usuarios get(@PathVariable int id){
		return usuarioService.findById(id).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
	}
	
	@DeleteMapping("/usuarios/{id}")
	void delete(@PathVariable int id) {
		usuarioService.deleteById(id);
	}
	
	@PutMapping("/usuarios")
	ResponseEntity<Usuarios> update(@RequestBody Usuarios user) {
		return ResponseEntity.ok(usuarioService.save(user));
	}
	
	@GetMapping("/usuarios/busca")
	Iterable<Usuarios> getByNameEmail(@RequestBody SearchDTO search) {
		if(!search.getNome().isBlank() && !search.getEmail().isBlank())
			return usuarioService.findByNomeAndEmail(search.getNome(), search.getEmail());
		else
			return usuarioService.findByNomeOrEmail(search.getNome(), search.getEmail());
	}
}
