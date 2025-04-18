package com.example.usuarios_servicio;

import com.example.usuarios_servicio.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UsuariosServicioApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
void testRegistroYLogin() {
    Usuario usuario = new Usuario(null, "Carlos", "Hernández", "Ingeniería", "carlos@mail.com", "1234", "ESTUDIANTE");
    ResponseEntity<String> registro = restTemplate.postForEntity("/auth/registro", usuario, String.class);
    assertThat(registro.getStatusCode()).isEqualTo(HttpStatus.OK);

    Map<String, String> login = Map.of("email", "carlos@mail.com", "password", "1234");
    ResponseEntity<Map> response = restTemplate.postForEntity("/auth/login", login, Map.class);
    assertThat(response.getBody()).containsKey("token");
}

}
