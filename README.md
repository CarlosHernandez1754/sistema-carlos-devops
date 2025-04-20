# Sistema Educativo - Microservicios con DevOps y Monitorización

Este proyecto implementa un sistema educativo basado en microservicios, integrando prácticas de DevOps, pruebas automatizadas, CI/CD, contenedores Docker y herramientas de monitorización.

---

## Tabla de Contenidos
- [Arquitectura](#arquitectura)
- [Tecnologías](#tecnologías)
- [Ejecución](#ejecución)
- [Pruebas](#pruebas)
- [CI/CD](#cicd)
- [Monitorización](#monitorización)
- [Servicios y Puertos](#servicios-y-puertos)
- [Autor](#autor)

---

## Arquitectura

El sistema está compuesto por los siguientes componentes:

- **usuarios-servicio**: Gestión de usuarios y autenticación.
- **asignaturas-servicio**: CRUD de asignaturas.
- **matriculas-servicio**: Gestión de matrículas.
- **config-server**: Configuración centralizada.
- **eureka-server**: Registro y descubrimiento de servicios.
- **MongoDB**: Base de datos NoSQL.
- **Prometheus y Grafana**: Monitorización.

Todos orquestados mediante Docker Compose.

---

## Tecnologías

- Java 17 + Spring Boot 3
- Spring Data MongoDB
- Spring Cloud (Config y Eureka)
- Docker + Docker Compose
- GitHub Actions
- Prometheus + Grafana
- JUnit 5 + Mockito + WebTestClient

---

## Ejecución

1. Clonar el repositorio:
```bash

2. Construir los servicios:
```bash
mvn clean install
```
3. Levantar los contenedores:
```bash
docker-compose up --build
```
4. Acceder a los servicios:
- Eureka: http://localhost:8761
- Prometheus: http://localhost:9090

---

## Pruebas

### Unitarias (Mockito)
- Pruebas en `usuarios-servicio`, `asignaturas-servicio`, `matriculas-servicio`

### Integración (WebTestClient)
- Validación de endpoints REST con respuestas reales

Ejecutar todas:
```bash
mvn test
```

---

## CI/CD

- Automatizado con GitHub Actions
- Flujo definido en `.github/workflows/test.yml`
- Ejecuta pruebas en cada push o pull request

---

## Monitorización

- Spring Boot Actuator + Prometheus para métricas
- Grafana para visualización
- Configurado en `docker-compose.yml` y `prometheus.yml`

---

## Servicios y Puertos

 Servicio              Puerto  Descripción                

 usuarios-servicio      8000    Gestión de usuarios        
 asignaturas-servicio   8002    Gestión de asignaturas     
 matriculas-servicio    8003    Gestión de matrículas      
 config-server          8888    Configuración centralizada 
 eureka-server          8761    Descubrimiento de servicios
 prometheus             9090    Recolección de métricas    
 mongodb                27017   Base de datos NoSQL        
