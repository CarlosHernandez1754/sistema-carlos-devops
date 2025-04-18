package com.example.asignaturas_servicio.service;

import com.example.asignaturas_servicio.model.Asignatura;
import java.util.List;
import java.util.Optional;

public interface AsignaturaService {
    List<Asignatura> obtenerTodas();
    Optional<Asignatura> obtenerPorId(Long id);
    Asignatura guardar(Asignatura asignatura);
    Asignatura actualizar(Long id, Asignatura asignatura);
    void eliminar(Long id);
}
