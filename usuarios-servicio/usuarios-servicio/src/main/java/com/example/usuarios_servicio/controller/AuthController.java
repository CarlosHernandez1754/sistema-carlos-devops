package com.example.usuarios_servicio.controller;

import com.example.usuarios_servicio.model.Usuario;
import com.example.usuarios_servicio.repository.UsuarioRepository;
import com.example.usuarios_servicio.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired private UsuarioRepository repo;
    @Autowired private PasswordEncoder encoder;
    @Autowired private JwtUtil jwtUtil;

    @PostMapping("/registro")
public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
    usuario.setPassword(encoder.encode(usuario.getPassword()));

    if (usuario.getRol() == null || usuario.getRol().isBlank()) {
        usuario.setRol("ESTUDIANTE");
    }

    repo.save(usuario);
    return ResponseEntity.ok("Usuario registrado exitosamente");
}


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {
        Usuario user = repo.findByEmail(credenciales.get("email")).orElse(null);
        if (user != null && encoder.matches(credenciales.get("password"), user.getPassword())) {
            String token = jwtUtil.generateToken(user.getEmail());
            return ResponseEntity.ok(Map.of("token", token));
        }
        return ResponseEntity.status(401).body("Credenciales incorrectas");
    }
}

@RestController
@RequestMapping("/usuarios")
class UsuarioController {

    @Autowired private UsuarioRepository repo;
    @Autowired private JwtUtil jwtUtil;

    public JwtUtil getJwtUtil() {
        return jwtUtil;
    }

    public void setJwtUtil(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/perfil")
public ResponseEntity<?> perfil(Principal principal) {
    Usuario usuario = repo.findByEmail(principal.getName()).orElse(null);

    if (usuario == null) {
        return ResponseEntity.status(404).body("Usuario no encontrado");
    }

    
    return ResponseEntity.ok(Map.of(
            "id", usuario.getId(),
            "nombre", usuario.getNombre(),
            "apellido", usuario.getApellido(),
            "carrera", usuario.getCarrera(),
            "email", usuario.getEmail(),
            "rol", usuario.getRol()
    ));
}

}