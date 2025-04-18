package com.example.matriculas_servicio.repository;

import com.example.matriculas_servicio.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    List<Matricula> findByEstudianteId(Long estudianteId);
    List<Matricula> findByAsignaturaId(Long asignaturaId);
}
