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

import com.services.cinelinx.model.Combo;
import com.services.cinelinx.repository.ComboRepository;

@Controller
@RequestMapping("/combos")
public class ComboController {
	
    @Autowired
    private ComboRepository comboRepository;

    @GetMapping("")
    ModelAndView combos(@PageableDefault(sort = "precio", direction = Sort.Direction.DESC)
    Pageable pageable) {
        Page<Combo> combos = comboRepository.findAll(pageable);

        return new ModelAndView("combos")
                .addObject("combos", combos);
    }

    @GetMapping("/{id}")
    ModelAndView detallesCombo(@PathVariable Integer id) {
        Combo combo = comboRepository.getOne(id);
        return new ModelAndView("combo")
                .addObject("combo", combo);
    }
}
