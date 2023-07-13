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

import com.services.cinelinx.model.Combo;
import com.services.cinelinx.model.Categoria;
import com.services.cinelinx.repository.ComboRepository;
import com.services.cinelinx.repository.CategoriaRepository;

@Controller
@RequestMapping("/admin/combos")
public class AdminComboController {

    @Autowired
    private ComboRepository comboRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("")
    ModelAndView combos(@PageableDefault(sort = "precio", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Combo> combos = comboRepository.findAll(pageable);

        return new ModelAndView("admin/combos")
                .addObject("combos", combos);
    }

    @GetMapping("/nuevo")
    ModelAndView nuevoCombo() {
        List<Categoria> categorias = categoriaRepository.findAll(Sort.by("nombre"));

        return new ModelAndView("admin/nuevo-combo")
                .addObject("combo", new Combo())
                .addObject("categorias", categorias);
    }

    @PostMapping("/nuevo")
    ModelAndView crearCombo(@Validated Combo combo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            List<Categoria> categorias = categoriaRepository.findAll(Sort.by("nombre"));

            return new ModelAndView("admin/nuevo-combo")
                    .addObject("combo", combo)
                    .addObject("categorias", categorias);
        }

        comboRepository.save(combo);
        return new ModelAndView("redirect:/admin/combos");
    }

    @GetMapping("/{id}/editar")
    ModelAndView editarCombo(@PathVariable Integer id) {
        Combo combo = comboRepository.getOne(id);
        // Obtener la lista de categorías para mostrar en el formulario
        List<Categoria> categorias = categoriaRepository.findAll(Sort.by("titulo"));

        return new ModelAndView("admin/editar-combo")
                .addObject("combo", combo)
                .addObject("categorias", categorias);
    }

    @PostMapping("/{id}/editar")
    ModelAndView actualizarCombo(@PathVariable Integer id,
                                 @Validated Combo combo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<Categoria> categorias = categoriaRepository.findAll(Sort.by("titulo"));

            return new ModelAndView("admin/editar-combo")
                    .addObject("combo", combo)
                    .addObject("categorias", categorias);
        }

        Combo comboFromDb = comboRepository.getOne(id);
        comboFromDb.setTitulo(combo.getTitulo());
        comboFromDb.setPrecio(combo.getPrecio());
        comboFromDb.setDescripcion(combo.getDescripcion());
        comboFromDb.setRutaImagen(combo.getRutaImagen());
        comboFromDb.setCategorias(combo.getCategorias());

        comboRepository.save(comboFromDb);

        return new ModelAndView("redirect:/admin/combos");
    }

    @PostMapping("/{id}/eliminar")
    String eliminarCombo(@PathVariable Integer id) {
        Combo combo = comboRepository.getOne(id);
        comboRepository.delete(combo);
        return "redirect:/admin/combos";
    }
}