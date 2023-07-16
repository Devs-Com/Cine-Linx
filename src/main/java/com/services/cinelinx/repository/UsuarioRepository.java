package com.services.cinelinx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.services.cinelinx.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    public Usuario findByEmail(String email);
}
