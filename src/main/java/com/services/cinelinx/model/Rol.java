package com.services.cinelinx.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "rol")
public class Rol {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	
	public Rol(String nombre) {
		this.nombre = nombre;
	}

	public Rol() {
	}

	public Rol(Long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
}
