package com.services.cinelinx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.services.cinelinx.dto.UsuarioRegistroDTO;
import com.services.cinelinx.service.UsuarioService;

@Controller
@RequestMapping("/registro")
public class RegistroUsuarioController {
    private UsuarioService usuarioService;

    public RegistroUsuarioController(UsuarioService usuarioService) {
        super();
        this.usuarioService = usuarioService;
    }

    @ModelAttribute("usuario")
    public UsuarioRegistroDTO retornarNuevUsuarioRegistroDTO() {
        return new UsuarioRegistroDTO();
    }

    @GetMapping
    public String montrarFormularioDeRegistro() {
        return "registro";
    }

    @PostMapping
    public String regitrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO){
        usuarioService.guardar(registroDTO);
		return "redirect:/registro?exito";
    }
}
