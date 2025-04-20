package com.example.matriculas_servicio;
import org.springframework.test.context.ActiveProfiles;
import com.example.matriculas_servicio.model.Matricula;
import com.example.matriculas_servicio.repository.MatriculaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class MatriculaControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private MatriculaRepository matriculaRepository;

    @BeforeEach
    void setUp() {
        matriculaRepository.deleteAll();
        Matricula m = new Matricula();
        m.setEstudianteId(1L);
        m.setAsignaturaId(2L);
        matriculaRepository.save(m);
    }

    @Test
    void testObtenerTodasLasMatriculas() {
        webTestClient.get()
                .uri("/matriculas")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Matricula.class)
                .hasSize(1);
    }
}
