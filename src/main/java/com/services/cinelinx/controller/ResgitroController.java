package com.services.cinelinx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ResgitroController {

    @GetMapping("/login")
    public String iniciarSesion(){
        return "login";
    }

    /*@GetMapping("/")
    public String verPaginadeInicio(){
        return "index";
    }*/
}
