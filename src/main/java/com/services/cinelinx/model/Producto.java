package com.services.cinelinx.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data 
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproducto")
    private Integer id;

    @NotBlank
    private String titulo;

    @NotNull
    private BigDecimal precio;

    @NotBlank
    private String rutaImagen;

    @Transient
    private MultipartFile imagen;
} 