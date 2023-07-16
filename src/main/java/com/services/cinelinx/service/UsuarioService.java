package com.services.cinelinx.service;


import org.springframework.security.core.userdetails.UserDetailsService;

import com.services.cinelinx.dto.UsuarioRegistroDTO;
import com.services.cinelinx.model.Usuario;

public interface UsuarioService extends UserDetailsService{
    public Usuario guardar(UsuarioRegistroDTO registroDTO);
	
	
}
