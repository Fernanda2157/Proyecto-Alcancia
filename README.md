# Proyecto Alcancía Digital — Rama 2: Monolítico por Capas

Este repositorio contiene la evolución del proyecto **Alcancía Digital** hacia una arquitectura limpia y ordenada. En esta **Rama 2**, la aplicación ha sido reestructurada bajo el patrón de diseño **Monolítico por Capas**, separando estrictamente las responsabilidades de presentación, lógica de negocio y acceso a datos para eliminar el código espagueti inicial.

---

## 🏛️ Arquitectura del Sistema

A diferencia de la versión inicial, esta rama organiza los componentes del software en capas claramente definidas:

1. **Capa de Presentación (UI / Controladores):** 
   * Encargada de recibir las peticiones HTTP y retornar las vistas al usuario.
   * Desarrollada con **Thymeleaf**, **HTML5** y **CSS** para renderizar dinámicamente el estado de las alcancías (ej. barras de progreso, etiquetas de estado `ACTIVA`).
2. **Capa de Negocio (Servicios):**
   * Contiene toda la lógica financiera del sistema.
   * Calcula de forma dinámica el porcentaje de progreso de ahorro, el dinero restante necesario para cumplir la meta y las validaciones correspondientes.
3. **Capa de Datos (Persistencia / Repositorios):**
   * Maneja el acceso a la base de datos de manera aislada, abstrayendo las consultas de la lógica de negocio mediante Spring Data JPA.

---

## ✨ Características de la Rama 2

* **Visualización de Alcancías:** Un panel principal ("Todas las Alcancías") estructurado en tarjetas (*cards*) individuales para cada registro.
* **Cálculo de Progreso en Tiempo Real:** Renderizado automático de barras de progreso porcentuales (ej. `9.8%` completado).
* **Métricas Clave por Alcancía:** 
  * **Saldo:** Dinero ahorrado actualmente.
  * **Meta:** Objetivo financiero establecido.
  * **Falta:** La diferencia exacta (Meta - Saldo) calculada de forma segura en la capa de negocio.
* **Gestión de Estados:** Soporte para estados visuales del contenedor de ahorro (como la etiqueta `ACTIVA`).
* **Operaciones Principales:** Acceso directo a la creación de nuevas alcancías (`+ Nueva Alcancía`) y auditoría de movimientos (`Ver detalle`).

---

## 🛠️ Tecnologías Utilizadas

* **Backend:** Java con **Spring Boot** (Spring MVC, Spring Data JPA).
* **Frontend:** **Thymeleaf** para el motor de plantillas y estilos CSS responsivos.
* **Herramientas de Desarrollo:** Git para el control de versiones ramificado.

---

## 🚀 Instalación y Ejecución Local

### Prerrequisitos
* Java JDK 17 o superior.
* Maven 3.x instalado.

### Pasos para iniciar la aplicación

1. **Clonar el repositorio y situarse en la rama de la evaluación:**
```bash
   git clone [https://github.com/Fernanda2157/Proyecto-Alcancia.git](https://github.com/Fernanda2157/Proyecto-Alcancia.git)
   cd Proyecto-Alcancia
   git checkout feature/monolitico-por-capas