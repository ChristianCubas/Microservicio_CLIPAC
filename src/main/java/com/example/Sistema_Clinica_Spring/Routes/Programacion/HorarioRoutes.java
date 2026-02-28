package com.example.Sistema_Clinica_Spring.Routes.Programacion;

import com.example.Sistema_Clinica_Spring.Models.Programacion.Horario;
import com.example.Sistema_Clinica_Spring.Services.Programacion.ServiceHorario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HorarioRoutes {
    @Autowired
    ServiceHorario serviceHorario;

    @GetMapping("/horarios")
    public String listarHorarios(Model model){
        List<Horario> listadoHorarios = serviceHorario.listarHorarios();
        model.addAttribute("horarios",listadoHorarios);
        model.addAttribute("title","Horarios");
        return "dashboard/Apartados/Horarios";
    }

    @GetMapping("/horarios/nuevo")
    public String nuevoHorario(Model model) {

        model.addAttribute("horario", new Horario());
        model.addAttribute("accion", "/horarios/guardar");
        model.addAttribute("titulo", "Crear Horario");
        model.addAttribute("botonTexto", "Guardar");

        return "dashboard/CRUDs/FormHorario";
    }

    @GetMapping("/horarios/editar/{id}")
    public String editarHorario(@PathVariable Long id, Model model) {

        Horario horario = serviceHorario.obtenerHorario(id);

        model.addAttribute("horario", horario);
        model.addAttribute("accion", "/horarios/actualizar/" + id);
        model.addAttribute("titulo", "Editar Horario");
        model.addAttribute("botonTexto", "Actualizar");

        return "dashboard/CRUDs/FormHorario";
    }

    @PostMapping("/horarios/guardar")
    public String guardarHorario(@ModelAttribute Horario horario) {
        serviceHorario.crearHorario(horario);
        return "redirect:/horarios";
    }

    @PostMapping("/horarios/actualizar/{id}")
    public String actualizarHorarios(@PathVariable Long id, @ModelAttribute Horario horario){
        serviceHorario.actualizarHorario(id,horario);
        return "redirect:/horarios";
    }
}
