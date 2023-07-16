package com.services.cinelinx.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.services.cinelinx.model.Genero;

public interface GeneroRepository extends JpaRepository<Genero, Integer> {
}
