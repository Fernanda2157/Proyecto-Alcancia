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
   * *Descripción:* Implementación inicial donde la lógica, datos e interfaz están acoplados.

2. 🥞 **[Rama: Monolítico por Capas](https://github.com/Fernanda2157/Proyecto-Alcancia/tree/feature/monolitico-capas)**
   * *Descripción:* Refactorización utilizando el patrón **MVC** con capas de **Controller, Service y Repository**.

3. ⬢ **[Rama: Enfoque DDD](https://github.com/Fernanda2157/Proyecto-Alcancia/tree/feature/enfoque-ddd)**
   * *Descripción:* Arquitectura basada en **Domain-Driven Design**. Aísla el dominio del negocio del resto de la infraestructura técnica.

---

## 🛠️ Tecnologías Utilizadas
* **Lenguaje:** Java 17
* **Framework:** Spring Boot 3.2.5
* **Persistencia:** H2 Database (In-memory)
* **Gestión de Versiones:** Git & GitHub
---

## 🚀 Cómo ejecutar y navegar el proyecto

1. Clone este repositorio en su máquina local:
```bash
   git clone [https://github.com/Fernanda2157/alcancia.git](https://github.com/Fernanda2157/alcancia.git)
