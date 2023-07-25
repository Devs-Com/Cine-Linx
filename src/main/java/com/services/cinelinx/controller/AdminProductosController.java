package com.services.cinelinx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.services.cinelinx.model.Categoria;
import com.services.cinelinx.model.Producto;
import com.services.cinelinx.repository.CategoriaRepository;
import com.services.cinelinx.repository.ProductoRepository;
import com.services.cinelinx.service.FileSystemStorageService;

@Controller
@RequestMapping("/admin/productos")
public class AdminProductosController {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private FileSystemStorageService fileSystemStorageService;

    @GetMapping("")
    ModelAndView productos(@PageableDefault(sort = "nombre", size = 5) Pageable pageable) {
        Page<Producto> productos = productoRepository.findAll(pageable);

        return new ModelAndView("admin/productos")
                .addObject("productos", productos);
    }

    @GetMapping("/nuevo")
    ModelAndView nuevoProducto() {
        List<Categoria> categorias = categoriaRepository.findAll(Sort.by("titulo"));

        return new ModelAndView("admin/nuevo-producto")
                .addObject("producto", new Producto())
                .addObject("categorias", categorias);
    }

    /*
     * TODO: Corregir el envio del idProducto que se envia a la tabla Categorias
     * columna idCategoria. Ademas que
     * capture el idCategoria en la tabla de productos columna idCatagoria.
     */
    @PostMapping("/nuevo")
    ModelAndView crearProducto(@Validated Producto producto, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || producto.getImagen().isEmpty()) {
            if (producto.getImagen().isEmpty()) {
                bindingResult.rejectValue("imagen", "MultipartNotEmpty");
            }

            List<Categoria> categorias = categoriaRepository.findAll(Sort.by("titulo"));

            return new ModelAndView("admin/nuevo-producto")
                    .addObject("producto", producto)
                    .addObject("categorias", categorias);
        }
        String rutaImagen = fileSystemStorageService.storage(producto.getImagen());
        producto.setRutaImagen(rutaImagen);

        productoRepository.save(producto);
        return new ModelAndView("redirect:/admin/productos");
    }

    @GetMapping("/{id}/editar")
    ModelAndView editarProducto(@PathVariable Integer id) {
        Producto producto = productoRepository.getOne(id);
        // Obtener la lista de categorías para mostrar en el formulario
        List<Categoria> categorias = categoriaRepository.findAll(Sort.by("titulo"));

        return new ModelAndView("admin/editar-producto")
                .addObject("producto", producto)
                .addObject("categorias", categorias);
    }

    @PostMapping("/{id}/editar")
    ModelAndView actualizarProducto(@PathVariable Integer id,
            @Validated Producto producto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<Categoria> categorias = categoriaRepository.findAll(Sort.by("titulo"));

            return new ModelAndView("admin/editar-producto")
                    .addObject("producto", producto)
                    .addObject("categorias", categorias);
        }

        Producto productoFromDb = productoRepository.getOne(id);
        productoFromDb.setNombre(producto.getNombre());
        productoFromDb.setPrecio(producto.getPrecio());
        productoFromDb.setDescripcion(producto.getDescripcion());
        productoFromDb.setCategoria(producto.getCategoria());

        if (!producto.getImagen().isEmpty()) {
            fileSystemStorageService.delete(productoFromDb.getRutaImagen());
            String rutaImagen = fileSystemStorageService.storage(producto.getImagen());
            productoFromDb.setRutaImagen(rutaImagen);
        }

        productoRepository.save(productoFromDb);

        return new ModelAndView("redirect:/admin/productos");
    }

    @PostMapping("/{id}/eliminar")
    String eliminarProducto(@PathVariable Integer id) {
        Producto producto = productoRepository.getOne(id);
        productoRepository.delete(producto);
        fileSystemStorageService.delete(producto.getRutaImagen());
        return "redirect:/admin/productos";
    }
}