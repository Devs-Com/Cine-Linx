package com.services.cinelinx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.services.cinelinx.model.Producto;
import com.services.cinelinx.repository.ProductoRepository;

@Controller
@RequestMapping("")
public class ProductoController {
	
    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("")
    ModelAndView index_confiteria() {
        List<Producto> Productos = productoRepository
                .findAll(PageRequest.of(0, 4, Sort.by("precio").descending()))
                .toList();

        return new ModelAndView("index_confiteria")
                .addObject("Productos", Productos);
    }
}