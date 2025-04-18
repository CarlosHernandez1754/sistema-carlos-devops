package com.example.usuarios_servicio;

import com.example.usuarios_servicio.controller.AuthController;
import com.example.usuarios_servicio.model.Usuario;
import com.example.usuarios_servicio.repository.UsuarioRepository;
import com.example.usuarios_servicio.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthControllerUnitTest {

    @Mock
    private UsuarioRepository repo;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegistrarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setPassword("1234");

        when(encoder.encode("1234")).thenReturn("hashed_password");

        ResponseEntity<?> response = controller.registrar(usuario);

        assertEquals(200, response.getStatusCodeValue());
        verify(repo, times(1)).save(any(Usuario.class));
    }

    @Test
    void testLoginExitoso() {
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");
        usuario.setPassword("hashed");

        when(repo.findByEmail("test@example.com")).thenReturn(Optional.of(usuario));
        when(encoder.matches("1234", "hashed")).thenReturn(true);
        when(jwtUtil.generateToken("test@example.com")).thenReturn("token123");

        ResponseEntity<?> response = controller.login(Map.of("email", "test@example.com", "password", "1234"));

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().toString().contains("token123"));
    }
}
