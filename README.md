# Alcancía Digital: Proyecto de Arquitectura Hexagonal

Este proyecto es una implementación de una aplicación de gestión de ahorros ("Alcancía Digital") desarrollada utilizando **Arquitectura Hexagonal (Ports and Adapters)** y principios de **Domain-Driven Design (DDD)**.

## 🚀 Arquitectura
El objetivo principal es mantener la lógica de negocio aislada de los detalles técnicos (base de datos, frameworks, UI).

- **`domain`**: Contiene las entidades (`Alcancia`), objetos de valor (`Monto`) y las reglas de negocio críticas. Es la capa más interna y no depende de ninguna tecnología externa.
- **`application`**: Orquestación de casos de uso a través de servicios (`AlcanciaApplicationService`). Aquí se transforman comandos en acciones de negocio.
- **`infrastructure`**: Implementación de adaptadores para la persistencia (`JPA/H2`) y los controladores web (`Spring Boot MVC`).

## 🛠 Tecnologías
- **Java 17**
- **Spring Boot 3.x**
- **Thymeleaf** (Capa de presentación)
- **H2 Database** (Persistencia en memoria)
- **Maven** (Gestión de dependencias)

## 🎯 Características Destacadas
1. **Validación Estricta:** La lógica de depósito asegura que la meta de ahorro sea respetada, bloqueando depósitos que excedan el monto faltante desde el modelo de dominio.
2. **Arquitectura Desacoplada:** El dominio está protegido, permitiendo cambios en la persistencia o en el framework sin afectar las reglas de negocio.
3. **UI Dinámica:** Visualización en tiempo real del progreso de ahorro con lógica condicional en la capa de vista.

## ⚙️ Cómo ejecutar
1. Asegúrate de tener **JDK 17** instalado.
2. Clona el repositorio y navega a la carpeta del proyecto.
3. Compila el proyecto:
```bash
   mvn clean compile
Ejecuta la aplicación:

Bash
   mvn spring-boot:run
Acceso al sistema: Abre tu navegador y dirígete a:
http://localhost:8080/

Consola de base de datos H2: Para inspeccionar los datos en tiempo real:
http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:alcancia_ddd

User Name: sa

Password: (dejar vacío)

Desarrollado para la Evaluación Sumativa de Arquitectura de Sistemas.