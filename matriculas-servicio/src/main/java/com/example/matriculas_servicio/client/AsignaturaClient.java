package com.example.matriculas_servicio.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Map;

@FeignClient(name = "asignaturas-servicio")
public interface AsignaturaClient {
    @GetMapping("/asignaturas/{id}")
    Map<String, Object> obtenerAsignatura(@PathVariable("id") Long id);
}
