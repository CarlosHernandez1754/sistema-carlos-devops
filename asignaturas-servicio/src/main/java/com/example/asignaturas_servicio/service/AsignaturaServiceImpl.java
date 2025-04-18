package com.example.asignaturas_servicio.service;



import com.example.asignaturas_servicio.model.Asignatura;
import com.example.asignaturas_servicio.repository.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsignaturaServiceImpl implements AsignaturaService {

    @Autowired
    private AsignaturaRepository repository;

    @Override
    public List<Asignatura> obtenerTodas() {
        return repository.findAll();
    }

    @Override
    public Optional<Asignatura> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Asignatura guardar(Asignatura asignatura) {
        return repository.save(asignatura);
    }

    @Override
    public Asignatura actualizar(Long id, Asignatura asignatura) {
        if (repository.existsById(id)) {
            asignatura.setId(id);
            return repository.save(asignatura);
        }
        return null;
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
