package com.services.cinelinx.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    ModelAndView index_confiteria() {
        List<Producto> Productos = productoRepository
                .findAll();

        return new ModelAndView("index_confiteria")
                .addObject("Productos", Productos);
    }
}