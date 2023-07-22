package com.services.cinelinx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.services.cinelinx.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
