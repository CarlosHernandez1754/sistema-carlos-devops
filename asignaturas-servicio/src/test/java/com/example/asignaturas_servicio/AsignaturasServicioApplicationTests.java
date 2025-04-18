package com.example.asignaturas_servicio;


import com.example.asignaturas_servicio.model.Asignatura;
import com.example.asignaturas_servicio.repository.AsignaturaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AsignaturasServicioApplicationTests {

    @Autowired
    private AsignaturaRepository repository;

    @Test
    void crearAsignaturaTest() {
        Asignatura asignatura = new Asignatura();
        asignatura.setNombre("Matemáticas");
        asignatura.setDescripcion("Álgebra y cálculo");
        asignatura.setDocenteId(1L);

        Asignatura guardada = repository.save(asignatura);
        assertThat(guardada.getId()).isNotNull();
        assertThat(guardada.getNombre()).isEqualTo("Matemáticas");
    }
}