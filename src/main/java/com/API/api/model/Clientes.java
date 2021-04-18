package com.API.api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Clientes")
public class Clientes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	private String nome;
	@Column(name = "datacadastro", length = 30, nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	
	@Column(length = 14, nullable = false)
	private String cpfCnpj;
	
	@Column(length = 50, nullable = false)
	private String logradouro;
	
	@Column(length = 40, nullable = false)
	private String cidade;
	
	@Column(length = 2, nullable = false)
	private char[] uf;
	
	@Column(length = 8, nullable = false)
	private char[] cep;
	
	@Column(length = 11)
	private String telefone;
	
	@Column(length = 100)
	private String email;
	
}
