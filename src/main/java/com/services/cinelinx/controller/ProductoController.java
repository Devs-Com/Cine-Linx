package com.services.cinelinx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.services.cinelinx.model.Producto;
import com.services.cinelinx.repository.ProductoRepository;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("")
    ModelAndView productos(@PageableDefault(sort = "precio", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Producto> productos = productoRepository.findAll(pageable);

        return new ModelAndView("productos")
                .addObject("productos", productos);
    }

    @GetMapping("/{id}")
    ModelAndView detallesProducto(@PathVariable Integer id) {
        Producto producto = productoRepository.getOne(id);
        return new ModelAndView("producto")
                .addObject("producto", producto);
    }
}
