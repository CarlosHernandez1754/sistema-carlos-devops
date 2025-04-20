package com.example.asignaturas_servicio;


import com.example.asignaturas_servicio.model.Asignatura;
import com.example.asignaturas_servicio.repository.AsignaturaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class AsignaturaControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @BeforeEach
    void setUp() {
        asignaturaRepository.deleteAll();
        Asignatura asignatura = new Asignatura();
        asignatura.setNombre("Historia");
        asignatura.setDescripcion("Curso de Historia Universal");
        asignatura.setDocenteId(10L);
        asignaturaRepository.save(asignatura);
    }

    @Test
    void testObtenerTodasLasAsignaturas() {
        webTestClient.get()
                .uri("/asignaturas")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Asignatura.class)
                .hasSize(1);
    }
}
