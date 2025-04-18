package com.example.matriculas_servicio.controller;

import com.example.matriculas_servicio.model.Matricula;
import com.example.matriculas_servicio.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    
    @PostMapping
    public ResponseEntity<?> crearMatricula(@RequestBody Matricula matricula,
                                            @RequestHeader("Authorization") String token) {
        try {
            Matricula creada = matriculaService.crearMatriculaConToken(matricula, token);
            return ResponseEntity.ok(creada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }

    
    @GetMapping
    public ResponseEntity<List<Matricula>> obtenerTodas() {
        return ResponseEntity.ok(matriculaService.obtenerTodas());
    }

    
    @GetMapping("/estudiante/{id}")
    public ResponseEntity<List<Matricula>> obtenerPorEstudiante(@PathVariable Long id) {
        return ResponseEntity.ok(matriculaService.obtenerPorEstudiante(id));
    }

    // Consultar por asignatura
    @GetMapping("/asignatura/{id}")
    public ResponseEntity<List<Matricula>> obtenerPorAsignatura(@PathVariable Long id) {
        return ResponseEntity.ok(matriculaService.obtenerPorAsignatura(id));
    }
}
