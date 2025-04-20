package com.example.usuarios_servicio;

import com.example.usuarios_servicio.model.Usuario;
import com.example.usuarios_servicio.repository.UsuarioRepository;
import com.example.usuarios_servicio.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class AuthControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private String token;

    @BeforeEach
    void setUp() {
        usuarioRepository.deleteAll();

        Usuario usuario = new Usuario();
        usuario.setEmail("admin@correo.com");
        usuario.setPassword("1234");
        usuario.setNombre("Administrador");

        usuarioRepository.save(usuario);
        token = jwtUtil.generateToken(usuario.getEmail());
    }

    @Test
    void testPerfilAutenticado() {
        webTestClient.get()
                .uri("/usuarios/perfil")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.email").isEqualTo("admin@correo.com");
    }
}

