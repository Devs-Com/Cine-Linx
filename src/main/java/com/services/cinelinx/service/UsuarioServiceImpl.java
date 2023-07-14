package com.services.cinelinx.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.services.cinelinx.dto.UsuarioRegistroDTO;
import com.services.cinelinx.model.Rol;
import com.services.cinelinx.model.Usuario;
import com.services.cinelinx.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  private UsuarioRepository usuarioRepository;

  public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
    super();
    this.usuarioRepository = usuarioRepository;
  }

  @Override
  public Usuario guardar(UsuarioRegistroDTO registroDTO) {
    Usuario usuario = new Usuario(registroDTO.getNombre(),
        registroDTO.getApellido(), registroDTO.getEmail(),
        passwordEncoder.encode(registroDTO.getPassword()), Arrays.asList(new Rol("ROLE_USER")));
    return usuarioRepository.save(usuario);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Usuario usuario = usuarioRepository.findByEmail(username);
    if (usuario == null) {
      throw new UsernameNotFoundException("Usuario o password inválidos");
    }
    return new User(usuario.getEmail(), usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
  }

  private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles) {
    return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
  }

}
