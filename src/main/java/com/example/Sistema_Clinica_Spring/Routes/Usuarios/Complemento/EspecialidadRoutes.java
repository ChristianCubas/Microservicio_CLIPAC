package com.example.Sistema_Clinica_Spring.Routes.Usuarios.Complemento;

import com.example.Sistema_Clinica_Spring.Models.Usuarios.Complemento.Especialidad;
import com.example.Sistema_Clinica_Spring.Services.Usuarios.Complemento.ServiceEspecialidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EspecialidadRoutes {
    @Autowired
    ServiceEspecialidad serviceEspecialidad;

    @GetMapping("/especialidades")
    public String listarEspecialidades(Model model){
        List<Especialidad> listadoEspecialidades = serviceEspecialidad.listarEspecialidades();
        model.addAttribute("especialidades",listadoEspecialidades);
        model.addAttribute("title","Especialidades");
        return "dashboard/Apartados/Especialidades";
    }
}
