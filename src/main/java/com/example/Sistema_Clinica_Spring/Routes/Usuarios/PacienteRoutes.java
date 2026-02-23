package com.example.Sistema_Clinica_Spring.Routes.Usuarios;

import com.example.Sistema_Clinica_Spring.Models.Usuarios.Paciente;
import com.example.Sistema_Clinica_Spring.Services.Usuarios.ServicePaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
public class PacienteRoutes {
    @Autowired
    private ServicePaciente servicePaciente;

    /* RUTAS GENERALES */

    @GetMapping("/")
    public String mostrarPaginaInicio(){
        return "index";
    }

    @GetMapping("/iniciarSesion")
    public String login(){
        return "login";
    }

    /* CREAR PACIENTE */

    @GetMapping("/pacientes/nuevo")
    public String nuevoPaciente(Model model){
        model.addAttribute("paciente", new Paciente());
        return "registrarPaciente";
    }

    @PostMapping("/pacientes/guardar")
    public String guardarPaciente(@ModelAttribute Paciente paciente, RedirectAttributes redirectAttributes){
        paciente.setEstado(1);
        paciente.setCreatedAt(LocalDateTime.now());
        servicePaciente.crearPaciente(paciente);

        // ← Agregar estos 2 líneas para el toastr
        redirectAttributes.addFlashAttribute("mensaje", "Paciente registrado exitosamente");
        redirectAttributes.addFlashAttribute("tipo", "success");

        return "redirect:/pacientes/nuevo";
    }
}
