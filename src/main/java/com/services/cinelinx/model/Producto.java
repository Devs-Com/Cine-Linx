package com.services.cinelinx.model;

import lombok.Data;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProducto", columnDefinition = "INT(11)")
    private Integer idProducto;

    @NotBlank
    private String nombre;

    @NotNull
    private BigDecimal precio;

    @NotBlank
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;
}