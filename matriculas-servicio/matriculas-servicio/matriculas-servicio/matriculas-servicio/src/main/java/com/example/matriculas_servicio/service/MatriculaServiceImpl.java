package com.example.matriculas_servicio.service;

import com.example.matriculas_servicio.client.AsignaturaClient;
import com.example.matriculas_servicio.client.UsuarioClient;
import com.example.matriculas_servicio.model.Matricula;
import com.example.matriculas_servicio.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MatriculaServiceImpl implements MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private UsuarioClient usuarioClient;

    @Autowired
private AsignaturaClient asignaturaClient;

    @Override
public Matricula crearMatriculaConToken(Matricula matricula, String token) {
    Map<String, Object> perfil = usuarioClient.obtenerPerfil(token);
    String rol = (String) perfil.get("rol");
    if (!"ESTUDIANTE".equalsIgnoreCase(rol)) {
        throw new RuntimeException("Solo los estudiantes pueden registrarse en asignaturas.");
    }

    // Validar que la asignatura exista
    try {
        asignaturaClient.obtenerAsignatura(matricula.getAsignaturaId());
    } catch (Exception e) {
        throw new RuntimeException("La asignatura no existe.");
    }

    Long estudianteId = Long.valueOf(perfil.get("id").toString());
    matricula.setEstudianteId(estudianteId);

    return matriculaRepository.save(matricula);
}
    @Override
    public List<Matricula> obtenerTodas() {
        return matriculaRepository.findAll();
    }

    @Override
    public List<Matricula> obtenerPorEstudiante(Long estudianteId) {
        return matriculaRepository.findByEstudianteId(estudianteId);
    }

    @Override
    public List<Matricula> obtenerPorAsignatura(Long asignaturaId) {
        return matriculaRepository.findByAsignaturaId(asignaturaId);
    }
}

