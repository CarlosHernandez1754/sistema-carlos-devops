package com.example.matriculas_servicio;

import com.example.matriculas_servicio.client.AsignaturaClient;
import com.example.matriculas_servicio.client.UsuarioClient;
import com.example.matriculas_servicio.model.Matricula;
import com.example.matriculas_servicio.repository.MatriculaRepository;
import com.example.matriculas_servicio.service.MatriculaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MatriculaServiceUnitTests {

    @Mock
    private MatriculaRepository matriculaRepository;

    @Mock
    private UsuarioClient usuarioClient;

    @Mock
    private AsignaturaClient asignaturaClient;

    @InjectMocks
    private MatriculaServiceImpl matriculaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearMatriculaConToken_rolEstudiante() {
        Matricula matricula = new Matricula();
        matricula.setAsignaturaId(1L);

        Map<String, Object> perfil = new HashMap<>();
        perfil.put("rol", "ESTUDIANTE");
        perfil.put("id", 123L);

        when(usuarioClient.obtenerPerfil("token123")).thenReturn(perfil);
        doNothing().when(asignaturaClient).obtenerAsignatura(1L);
        when(matriculaRepository.save(any(Matricula.class))).thenReturn(matricula);

        Matricula result = matriculaService.crearMatriculaConToken(matricula, "token123");

        assertEquals(123L, result.getEstudianteId());
        verify(matriculaRepository, times(1)).save(any(Matricula.class));
    }

    @Test
    void testObtenerTodas() {
        List<Matricula> lista = new ArrayList<>();
        lista.add(new Matricula());
        when(matriculaRepository.findAll()).thenReturn(lista);

        List<Matricula> resultado = matriculaService.obtenerTodas();

        assertEquals(1, resultado.size());
        verify(matriculaRepository, times(1)).findAll();
    }
}

