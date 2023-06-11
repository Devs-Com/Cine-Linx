package com.services.cinelinx.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Sala {
    @Id
    @Column(name = "idsala")
    private Integer id;
    private String nombre;
    private Integer capacidad;
    private String ubicacion;
}
