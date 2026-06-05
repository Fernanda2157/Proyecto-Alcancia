# 🪙 Proyecto Alcancía: Evolución de Arquitecturas de Software

Este proyecto consiste en el desarrollo de un sistema de gestión para una **Alcancía Digital**, implementado bajo tres enfoques arquitectónicos diferentes. El objetivo es demostrar la evolución del código, desde malas prácticas hasta patrones de diseño profesionales y altamente escalables.

La alcancía permite realizar operaciones esenciales como:
* Depósito de dinero (ahorrar).
* Retiro de dinero (romper alcancía o retiros parciales).
* Consulta de saldo actual.
* Historial de transacciones.

---

## 📑 Estructura del Proyecto (Ramas)

Para evaluar el proyecto, por favor navegue entre las diferentes ramas de este repositorio. Cada una contiene el código fuente correspondiente y un `README` detallado con su justificación teórica:

1. 🍝 **[Rama: Código Espagueti](https://github.com/Fernanda2157/Proyecto-Alcancia/tree/feature/codigo-espagueti)**
   * *Descripción:* Todo el sistema (lógica, datos e interfaz) concentrado en un único flujo desestructurado. Demuestra los problemas de acoplamiento y mantenibilidad.
   
2. 🥞 **[Rama: Monolítico por Capas](https://github.com/Fernanda2157/Proyecto-Alcancia/tree/feature/monolitico-capas)**
   * *Descripción:* Separación de responsabilidades de forma técnica en tres capas clásicas: Presentación (Controladores), Negocio (Servicios) y Datos (Repositorios).

3. ⬢ **[Rama: Enfoque DDD](https://github.com/TU_USUARIO/alcancia/tree/feature/enfoque-ddd)**
   * *Descripción:* Arquitectura guiada por el dominio del negocio. Centrada en la entidad `Alcancia`, separando las reglas puras del negocio de la infraestructura tecnológica.

---

## 🛠️ Tecnologías Utilizadas

* **Lenguaje de Programación:** Java
* **Framework/Librerías:** Spring Boot
* **Persistencia:** En memoria
* **Herramienta de Control de Versiones:** Git & GitHub

---

## 🚀 Cómo ejecutar y navegar el proyecto

1. Clone este repositorio en su máquina local:
```bash
   
