package com.services.cinelinx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.services.cinelinx.model.Pelicula;
import com.services.cinelinx.repository.PeliculaRepository;


@Controller
@RequestMapping("")
public class HomeController {
    
    @Autowired
    private PeliculaRepository peliculaRepository;

    @GetMapping("")
    ModelAndView index() {
        List<Pelicula> ultimasPeliculas = peliculaRepository
                .findAll(PageRequest.of(0, 4, Sort.by("fechaEstreno").descending()))
                .toList();

        return new ModelAndView("index")
                .addObject("ultimasPeliculas", ultimasPeliculas);
    }
}
