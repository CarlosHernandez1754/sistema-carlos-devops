package com.example.asignaturas_servicio;


import com.example.asignaturas_servicio.model.Asignatura;
import com.example.asignaturas_servicio.repository.AsignaturaRepository;
import com.example.asignaturas_servicio.service.AsignaturaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AsignaturaServiceUnitTests {

    @Mock
    private AsignaturaRepository repository;

    @InjectMocks
    private AsignaturaServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGuardarAsignatura() {
        Asignatura asignatura = new Asignatura();
        asignatura.setNombre("Matemáticas");

        when(repository.save(asignatura)).thenReturn(asignatura);

        Asignatura resultado = service.guardar(asignatura);

        assertNotNull(resultado);
        assertEquals("Matemáticas", resultado.getNombre());
        verify(repository, times(1)).save(asignatura);
    }

    @Test
    void testActualizarAsignatura_existente() {
        Long id = 1L;
        Asignatura asignatura = new Asignatura();
        asignatura.setNombre("Física");

        when(repository.existsById(id)).thenReturn(true);
        when(repository.save(any(Asignatura.class))).thenReturn(asignatura);

        Asignatura resultado = service.actualizar(id, asignatura);

        assertNotNull(resultado);
        assertEquals("Física", resultado.getNombre());
        verify(repository, times(1)).existsById(id);
        verify(repository, times(1)).save(asignatura);
    }
}
