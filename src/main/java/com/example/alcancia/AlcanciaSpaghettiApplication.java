package com.example.alcancia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import jakarta.persistence.*;
import java.util.Optional;

/**
 * RAMA 1: feature/codigo-espagueti
 * ANTIPATRÓN: God Object (Objeto Dios).
 * Todo el ecosistema de la aplicación colapsa deliberadamente en un único archivo.
 */
@SpringBootApplication
@Controller
@RequestMapping("/")
public class AlcanciaSpaghettiApplication {

   
    private final AlcanciaSpaghettiRepository repository;
    private String mensajeFlash = "";


    public AlcanciaSpaghettiApplication(AlcanciaSpaghettiRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(AlcanciaSpaghettiApplication.class, args);
    }

    @GetMapping
    public String index(Model model) {
        Optional<AlcanciaSpaghettiEntity> bdd = repository.findById(1L);

        if (bdd.isEmpty()) {
            model.addAttribute("ahorrista", "No configurado");
            model.addAttribute("saldo", 0.0);
            model.addAttribute("objetivo", 0.0);
            model.addAttribute("estado", "INACTIVA");
            model.addAttribute("progreso", 0.0);
            model.addAttribute("falta", 0.0);
            model.addAttribute("mensaje", "SISTEMA LIMPIO: Ingrese los datos para inicializar.");
            return "index";
        }

        AlcanciaSpaghettiEntity datos = bdd.get();
        model.addAttribute("ahorrista", datos.getAhorrista());
        model.addAttribute("saldo", datos.getSaldo());
        model.addAttribute("objetivo", datos.getObjetivo());
        model.addAttribute("estado", datos.getEstado());
        model.addAttribute("progreso", (datos.getObjetivo() > 0) ? (datos.getSaldo() / datos.getObjetivo() * 100) : 0);
        model.addAttribute("falta", Math.max(datos.getObjetivo() - datos.getSaldo(), 0));
        model.addAttribute("mensaje", mensajeFlash);
        
        mensajeFlash = ""; 
        return "index";
    }

    @PostMapping("/configurar")
    public String configurar(@RequestParam String nombre, @RequestParam double meta) {
        if (nombre == null || nombre.trim().isEmpty() || meta <= 0) {
            mensajeFlash = "ERROR: Datos de entrada inválidos.";
            return "redirect:/";
        }

        AlcanciaSpaghettiEntity config = new AlcanciaSpaghettiEntity();
        config.setId(1L);
        config.setAhorrista(nombre);
        config.setObjetivo(meta);
        config.setSaldo(0.0);
        config.setEstado("ACTIVA");

        repository.save(config);
        mensajeFlash = "Alcancía configurada con éxito para " + nombre + " con meta de $" + meta;
        return "redirect:/";
    }

    @PostMapping("/depositar")
    public String depositar(@RequestParam double monto) {
        Optional<AlcanciaSpaghettiEntity> bdd = repository.findById(1L);

        if (bdd.isEmpty()) {
            mensajeFlash = "ERROR: Primero debe configurar la alcancía.";
            return "redirect:/";
        }

        AlcanciaSpaghettiEntity datos = bdd.get();

        if (monto <= 0) {
            mensajeFlash = (monto == 0) ? "ERROR: No puedes depositar $0.00" 
                                         : "ERROR: No puedes depositar negativo!! monto=" + monto;
            return "redirect:/";
        }

        if (!"ACTIVA".equals(datos.getEstado())) {
            mensajeFlash = "ERROR: La alcancía ya está COMPLETADA.";
            return "redirect:/";
        }
        double cuantoFalta = datos.getObjetivo() - datos.getSaldo();
        if (monto > cuantoFalta) {
            mensajeFlash = "ERROR: El monto $" + monto + " excede el límite. Solo te faltan $" + String.format("%.2f", cuantoFalta) + " para alcanzar la meta.";
            return "redirect:/";
        }

        datos.setSaldo(datos.getSaldo() + monto);

        if (datos.getSaldo() >= datos.getObjetivo()) {
            datos.setSaldo(datos.getObjetivo()); 
            datos.setEstado("COMPLETADA");
            mensajeFlash = "DEPOSITO EXITOSO - meta alcanzada!!! estado=COMPLETADA";
        } else {
            mensajeFlash = "DEPOSITO EXITOSO - saldo actual: " + datos.getSaldo() + " | falta: " + (datos.getObjetivo() - datos.getSaldo());
        }

        repository.save(datos);
        return "redirect:/";
    }

    @PostMapping("/reiniciar")
    public String reiniciar() {
        Optional<AlcanciaSpaghettiEntity> bdd = repository.findById(1L);
        if (bdd.isPresent()) {
            AlcanciaSpaghettiEntity datos = bdd.get();
            datos.setSaldo(0.0);
            datos.setEstado("ACTIVA");
            repository.save(datos);
            mensajeFlash = "Alcancia reiniciada";
        }
        return "redirect:/";
    }
}
@Entity
@Table(name = "spaghetti_alcancia")
class AlcanciaSpaghettiEntity {
    @Id
    private Long id;
    private String ahorrista;
    private double saldo;
    private double objetivo;
    private String estado;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getAhorrista() { return ahorrista; }
    public void setAhorrista(String ahorrista) { this.ahorrista = ahorrista; }
    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }
    public double getObjetivo() { return objetivo; }
    public void setObjetivo(double objetivo) { this.objetivo = objetivo; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}

@Repository
interface AlcanciaSpaghettiRepository extends JpaRepository<AlcanciaSpaghettiEntity, Long> {
}