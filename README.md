# Alcancía Digital: Rama de Código Espagueti (Anti-patrón)

Este repositorio contiene una implementación inicial de la "Alcancía Digital" diseñada bajo el anti-patrón **"Objeto Dios" (God Object)**.

## ⚠️ Sobre esta rama
Esta implementación deliberadamente **no sigue** buenas prácticas de arquitectura. El objetivo de este código es servir como punto de comparación para demostrar las limitaciones de un diseño acoplado y la necesidad de aplicar arquitecturas limpias (como la Hexagonal).

## 🚩 Problemas Técnicos (Anti-patrones identificados)
1. **Violación del Principio de Responsabilidad Única (SRP):** La clase `AlcanciaSpaghettiApplication` lo hace todo:
   - Gestiona el arranque de Spring Boot.
   - Define el modelo de datos (`@Entity`).
   - Implementa la lógica del controlador web (`@Controller`).
   - Contiene la lógica de persistencia.
   - Ejecuta las reglas de negocio.
2. **Alto Acoplamiento:** Los cambios en la base de datos o en la interfaz obligan a modificar la lógica de negocio, haciendo el sistema frágil.
3. **Escalabilidad nula:** Debido a la mezcla de responsabilidades, añadir una nueva funcionalidad implica un alto riesgo de introducir errores en partes no relacionadas del código.

## 🛠 Tecnologías
- **Java 17**
- **Spring Boot 3.x**
- **Thymeleaf** (Capa de presentación integrada en el mismo archivo)
- **H2 Database** (Persistencia en memoria)

---
## ⚙️ Configuración de entorno
Para ejecutar esta versión:
1. Asegúrese de tener instalado **JDK 17** y **Maven**.
2. Compile y ejecute:
   ```bash
   mvn clean spring-boot:run
   Acceda a la aplicación en: http://localhost:8080
---
