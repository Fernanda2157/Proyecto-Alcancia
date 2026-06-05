package com.example.alcancia.infrastructure.controller;

import com.example.alcancia.application.dto.CrearAlcanciaCommand;
import com.example.alcancia.application.service.AlcanciaApplicationService;
import com.example.alcancia.domain.exception.AlcanciaException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/alcancia")
public class AlcanciaController {

    private final AlcanciaApplicationService service;

    public AlcanciaController(AlcanciaApplicationService service) {
        this.service = service;
    }

    @GetMapping({"/", "/lista"})
    public String lista(Model model) {
        model.addAttribute("alcancias", service.listarTodas());
        return "lista";
    }

    @GetMapping("/nueva")
    public String nuevaForm() { return "nueva"; }

    @PostMapping("/nueva")
    public String nuevaGuardar(@RequestParam String nombre,
                                @RequestParam double meta,
                                RedirectAttributes flash) {
        try {
            CrearAlcanciaCommand cmd = new CrearAlcanciaCommand();
            cmd.setNombreAhorrista(nombre);
            cmd.setMeta(meta);
            service.crearAlcancia(cmd);
            flash.addFlashAttribute("exito", "Alcancía creada para " + nombre);
        } catch (AlcanciaException e) {
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
                             @RequestParam double monto,
                             RedirectAttributes flash) {
        try {
            var resp = service.depositar(id, monto);
            flash.addFlashAttribute("exito", resp.getMensaje());
        } catch (AlcanciaException e) {
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
