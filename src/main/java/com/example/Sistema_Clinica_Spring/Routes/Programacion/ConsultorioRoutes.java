package com.example.Sistema_Clinica_Spring.Routes.Programacion;

import com.example.Sistema_Clinica_Spring.Models.Programacion.Consultorio;
import com.example.Sistema_Clinica_Spring.Services.Programacion.ServiceConsultorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ConsultorioRoutes {

    @Autowired
    private ServiceConsultorio serviceConsultorio;

    @GetMapping("/consultorios")
    public String listarConsultorios(Model model){
        List<Consultorio> consultorios = serviceConsultorio.listarConsultorios();
        model.addAttribute("consultorios",consultorios);
        model.addAttribute("title","Consultorios");
        return "dashboard/Apartados/Consultorios";
    }
}
