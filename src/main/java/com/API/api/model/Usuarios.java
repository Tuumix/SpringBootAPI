package com.API.api.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import enums.Perfil;
import enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Usuarios")
public class Usuarios {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 30)
	private String nome;
	
	@Column(name = "dataCadastro")
	@Temporal(TemporalType.DATE)
	@CreationTimestamp
	private Calendar dataCadastro;
	
	@Column(length = 15, nullable = false)
	private String login;
	
	@Column(length = 10)
	private String senha;
	
	@Column(length = 11)
	private String telefone;
	
	@Column(length = 100)
	private String email;
	
	@Column(length = 1)
	private Perfil perfil;
	
	@Column(length = 1)
	@Enumerated(EnumType.STRING)
	private Status status;
}
