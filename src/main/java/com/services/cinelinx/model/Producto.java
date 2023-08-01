package com.services.cinelinx.model;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

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

    private String rutaImagen;

    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "categorias", joinColumns = @JoinColumn(name = "idCategoria"), inverseJoinColumns = @JoinColumn(name = "titulo"))
    private List<Categoria> categoria;

    @Transient
    private MultipartFile imagen;
}