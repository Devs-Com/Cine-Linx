package com.services.cinelinx.repository;

import com.services.cinelinx.model.Pelicula;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {

    Page<Pelicula> findByGeneroIdGenero(Integer idGenero, Pageable pageable);
}
