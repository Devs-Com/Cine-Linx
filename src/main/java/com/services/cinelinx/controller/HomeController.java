package com.services.cinelinx.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.services.cinelinx.model.Genero;
import com.services.cinelinx.model.Pelicula;

import com.services.cinelinx.model.Sala;
import com.services.cinelinx.repository.GeneroRepository;

import com.services.cinelinx.repository.PeliculaRepository;
import com.services.cinelinx.repository.SalaRepository;

@Controller
@RequestMapping("/")
public class HomeController {

        @Autowired
        private PeliculaRepository peliculaRepository;

        @Autowired
        private SalaRepository salaRepository;

        @Autowired
        private GeneroRepository generoRepository;

        @GetMapping("")
        ModelAndView index() {
                List<Pelicula> ultimasPeliculas = peliculaRepository
                                .findAll(PageRequest.of(0, 4, Sort.by("fechaEstreno").descending()))
                                .toList();

                return new ModelAndView("index")
                                .addObject("ultimasPeliculas", ultimasPeliculas);
        }

        @GetMapping("/peliculas")
        ModelAndView listaPeliculas(@RequestParam(value = "generoSeleccionado", required = false) Integer idGenero,
                        @PageableDefault(sort = "fechaEstreno", direction = Sort.Direction.DESC) Pageable pageable) {
                Page<Pelicula> peliculas;
                List<Genero> generos = generoRepository.findAll(Sort.by("nombre"));

                if (idGenero != null) {
                        Optional<Genero> generoOptional = generoRepository.findById(idGenero);
                        if (generoOptional.isPresent()) {
                                Genero genero = generoOptional.get();
                                peliculas = peliculaRepository.findByGeneroIdGenero(idGenero, pageable);
                        } else {
                                // Manejo de error si el género no existe
                                // Por ejemplo, redirigir a una página de error
                                return new ModelAndView("error");
                        }
                } else {
                        peliculas = peliculaRepository.findAll(pageable);
                }

                return new ModelAndView("peliculas")
                                .addObject("peliculas", peliculas)
                                .addObject("generos", generos);
        }

        @GetMapping("/peliculas/{id}")
        ModelAndView detallesPelicula(@PathVariable Integer id) {
                Pelicula pelicula = peliculaRepository.getOne(id);
                Sala sala = salaRepository.getOne(1);
                return new ModelAndView("pelicula")
                                .addObject("pelicula", pelicula)
                                .addObject("sala", sala);
        }
}
