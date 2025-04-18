package com.example.asignaturas_servicio.controller;

import com.example.asignaturas_servicio.model.Asignatura;
import com.example.asignaturas_servicio.repository.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asignaturas")
public class AsignaturaController {

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    // 🔹 GET: Listar todas las asignaturas
    @GetMapping
    public List<Asignatura> listar() {
        return asignaturaRepository.findAll();
    }

    // 🔹 GET: Obtener una asignatura por ID
    @GetMapping("/{id}")
    public ResponseEntity<Asignatura> obtenerPorId(@PathVariable Long id) {
        return asignaturaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔹 POST: Crear una nueva asignatura
    @PostMapping
    public ResponseEntity<Asignatura> crear(@RequestBody Asignatura asignatura) {
        return ResponseEntity.ok(asignaturaRepository.save(asignatura));
    }

    // 🔹 PUT: Actualizar una asignatura existente
    @PutMapping("/{id}")
    public ResponseEntity<Asignatura> actualizar(@PathVariable Long id, @RequestBody Asignatura asignaturaActualizada) {
        return asignaturaRepository.findById(id)
                .map(asignatura -> {
                    asignatura.setNombre(asignaturaActualizada.getNombre());
                    asignatura.setDescripcion(asignaturaActualizada.getDescripcion());
                    asignatura.setDocenteId(asignaturaActualizada.getDocenteId());
                    return ResponseEntity.ok(asignaturaRepository.save(asignatura));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔹 DELETE: Eliminar una asignatura
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        return asignaturaRepository.findById(id)
                .map(asignatura -> {
                    asignaturaRepository.delete(asignatura);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
