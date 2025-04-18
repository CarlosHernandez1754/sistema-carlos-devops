package com.example.matriculas_servicio.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import java.util.Map;

@FeignClient(name = "usuarios-servicio")
public interface UsuarioClient {
    @GetMapping("/usuarios/perfil")
    Map<String, Object> obtenerPerfil(@RequestHeader("Authorization") String token);
}

