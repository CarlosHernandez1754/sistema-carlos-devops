package com.example.matriculas_servicio.service;

import com.example.matriculas_servicio.model.Matricula;

import java.util.List;

public interface MatriculaService {
    Matricula crearMatriculaConToken(Matricula matricula, String token);
    List<Matricula> obtenerTodas();
    List<Matricula> obtenerPorEstudiante(Long estudianteId);
    List<Matricula> obtenerPorAsignatura(Long asignaturaId);
}
