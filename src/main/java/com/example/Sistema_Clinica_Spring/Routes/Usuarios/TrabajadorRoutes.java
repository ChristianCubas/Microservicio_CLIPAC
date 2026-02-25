package com.example.Sistema_Clinica_Spring.Routes.Usuarios;

import com.example.Sistema_Clinica_Spring.Models.Usuarios.Trabajador;
import com.example.Sistema_Clinica_Spring.Services.Usuarios.ServiceTrabajador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TrabajadorRoutes {
    @Autowired
    private ServiceTrabajador serviceTrabajador;

    @GetMapping("/trabajadores")
    public String listarTrabajadores(Model model) {
        List<Trabajador> trabajadores = serviceTrabajador.listarTrabajadores();

        model.addAttribute("trabajadores", trabajadores);
        model.addAttribute("title", "Trabajadores");

        return "dashboard/Apartados/Trabajadores";
    }
}
