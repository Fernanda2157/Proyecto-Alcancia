package com.example.alcancia.controller;

import com.example.alcancia.service.AlcanciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/alcancia") // Ruta base de los recursos de alcancía
public class AlcanciaController {

    @Autowired
    private AlcanciaService service;

    // 1. Maneja la raíz del sistema y redirige a la lista
    @GetMapping("/")
    public String raiz() {
        return "redirect:/alcancia/lista";
    }

    // 2. Maneja el acceso desde el navegador (ruta base)
    @GetMapping("")
    public String index() {
        return "redirect:/alcancia/lista";
    }

    @GetMapping("/lista")
    public String lista(Model model) {
        model.addAttribute("alcancias", service.listarTodas());
        return "lista";
    }

    @GetMapping("/nueva")
    public String nuevaForm() {
        return "nueva";
    }

    @PostMapping("/nueva")
    public String nuevaGuardar(@RequestParam String nombre,
                                @RequestParam Double meta,
                                RedirectAttributes flash) {
        try {
            service.crearAlcancia(nombre, meta);
            flash.addFlashAttribute("exito", "Alcancía creada para " + nombre);
        } catch (Exception e) {
            flash.addFlashAttribute("error", e.getMessage());
            return "redirect:/alcancia/nueva";
        }
        return "redirect:/alcancia/lista";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        model.addAttribute("alcancia", service.buscarPorId(id));
        return "detalle";
    }

    @PostMapping("/{id}/depositar")
    public String depositar(@PathVariable Long id,
                             @RequestParam Double monto,
                             RedirectAttributes flash) {
        try {
            String msg = service.depositar(id, monto);
            flash.addFlashAttribute("exito", msg);
        } catch (Exception e) {
            flash.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/alcancia/" + id;
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id, RedirectAttributes flash) {
        service.eliminar(id);
        flash.addFlashAttribute("exito", "Alcancía eliminada");
        return "redirect:/alcancia/lista";
    }
}