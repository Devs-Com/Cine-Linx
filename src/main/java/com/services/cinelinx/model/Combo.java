package com.services.cinelinx.model;

import lombok.Data;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data 
@Entity
@Table (name = "combos")
public class Combo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_combo")
    private Integer id;

    @NotBlank
    private String titulo;

    @NotNull
    private BigDecimal precio;

    @NotBlank
    private String descripcion;

    @NotBlank
    private String rutaImagen;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categorias;
} 
