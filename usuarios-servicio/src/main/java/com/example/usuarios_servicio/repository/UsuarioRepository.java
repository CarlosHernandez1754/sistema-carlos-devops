package com.example.usuarios_servicio.repository;


import com.example.usuarios_servicio.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    Object findByUsername(String string);
}