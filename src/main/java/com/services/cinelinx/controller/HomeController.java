package com.services.cinelinx.controller;

import java.util.List;

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
import org.springframework.web.servlet.ModelAndView;

import com.services.cinelinx.model.Genero;
import com.services.cinelinx.model.Pelicula;
import com.services.cinelinx.repository.GeneroRepository;
import com.services.cinelinx.repository.PeliculaRepository;


@Controller
@RequestMapping("")
public class HomeController {
    
    @Autowired
    private PeliculaRepository peliculaRepository;

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
    ModelAndView listaPeliculas(@PageableDefault(sort = "fechaEstreno", direction = Sort.Direction.DESC)
                                        Pageable pageable) {
        Page<Pelicula> peliculas = peliculaRepository.findAll(pageable);
        List<Genero> generos = generoRepository.findAll(Sort.by("titulo"));
        return new ModelAndView("peliculas")
                .addObject("peliculas", peliculas)
                .addObject("generos", generos);
    }

    @GetMapping("/peliculas/{id}")
    ModelAndView detallesPelicula(@PathVariable Integer id) {
        Pelicula pelicula = peliculaRepository.getOne(id);
        return new ModelAndView("pelicula")
                .addObject("pelicula", pelicula);
    }
}
