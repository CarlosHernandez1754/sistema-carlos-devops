package com.example.matriculas_servicio.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long estudianteId;

    private Long asignaturaId;
}

