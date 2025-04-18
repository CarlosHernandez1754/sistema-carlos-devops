package com.example.asignaturas_servicio.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Map;

@FeignClient(name = "usuarios-servicio")
public interface UsuarioClient {

    @GetMapping("/usuarios/perfil")
    Map<String, Object> obtenerPerfil();
}
