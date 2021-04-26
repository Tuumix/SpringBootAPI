package com.API.api.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

import enums.Tipo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "livro_caixa")
public class LivroCaixa {
	
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JoinColumn(name = "id_cliente")
	@ManyToOne(optional = false)
	private Clientes id_cliente;
	
	@Column(name = "dataLancamento", nullable = false)
	@Temporal(TemporalType.DATE)
	@CreationTimestamp
	private Calendar dataLancamento;
	
	@Column(name = "descricao", length = 50, nullable = false)
	private String descricao;
	
	@Column(name = "tipo", length = 1, nullable = false)
	private Tipo tipo;
	
	@Column(name = "valor", nullable = false, precision = 10, scale = 2)
	private double valor;
}
