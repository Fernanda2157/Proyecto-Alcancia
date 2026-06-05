# Alcancía Digital: Proyecto de Alcancia Enfoque DDD

Este proyecto es una implementación de una aplicación de gestión de ahorros ("Alcancía Digital") desarrollada utilizando **Principios de **Domain-Driven Design (DDD)**.

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

---
## ⚙️ Configuración de entorno
Para ejecutar esta versión:
1. Asegúrese de tener instalado **JDK 17** y **Maven**.
2. Compile y ejecute:
   ```bash
   mvn clean spring-boot:run
   Acceda a la aplicación en: http://localhost:8080
---

  
