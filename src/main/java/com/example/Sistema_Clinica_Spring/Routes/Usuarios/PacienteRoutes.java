package com.example.Sistema_Clinica_Spring.Routes.Usuarios;

import com.example.Sistema_Clinica_Spring.Models.Usuarios.Paciente;
import com.example.Sistema_Clinica_Spring.Models.Usuarios.Trabajador;
import com.example.Sistema_Clinica_Spring.Services.Usuarios.ServicePaciente;
import com.example.Sistema_Clinica_Spring.Services.Usuarios.ServiceTrabajador;
import jakarta.servlet.http.HttpSession;
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

    @Autowired
    private ServiceTrabajador serviceTrabajador;

    /* RUTAS GENERALES */

    @GetMapping("/")
    public String mostrarPaginaInicio(){
        return "index";
    }

    @GetMapping("/iniciarSesion")
    public String logearTemplate(){
        return "login";
    }

    /* LOGEAR PACIENTE/TRABAJADOR */

    @PostMapping("/login")
    public String loginUsuario(String email, String contrasenia, Model model, HttpSession sesion, RedirectAttributes redirectAttributes){
        Paciente paciente = servicePaciente.logearPaciente(email,contrasenia);
        if (paciente != null){
            sesion.setAttribute("paciente",paciente);
            model.addAttribute("usuario", paciente);

            System.out.println("Paciente: "+paciente);

            return "registrarCita";
        }else{
            Trabajador trabajador = serviceTrabajador.logearTrabajador(email, contrasenia);
            if (trabajador != null){
                sesion.setAttribute("trabajador",trabajador);
                model.addAttribute("usuario",trabajador);

                System.out.println("Trabajador: "+trabajador);

                return "Dashboard/dashboard";
            }else{
                return "index";
            }
        }
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

        // Toastr
        redirectAttributes.addFlashAttribute("mensaje", "Paciente registrado exitosamente");
        redirectAttributes.addFlashAttribute("tipo", "success");

        return "redirect:/pacientes/nuevo";
    }
}
