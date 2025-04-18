package com.example.asignaturas_servicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AsignaturasServicioApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsignaturasServicioApplication.class, args);
	}

}
 