package com.example.Sistema_Clinica_Spring.Routes.Programacion;

import com.example.Sistema_Clinica_Spring.Models.Programacion.Consultorio;
import com.example.Sistema_Clinica_Spring.Services.Programacion.ServiceConsultorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/consultorios/nuevo")
    public String nuevoConsultorio(Model model) {
        model.addAttribute("consultorio", new Consultorio());
        model.addAttribute("title", "Nuevo Consultorio");
        model.addAttribute("accion", "/consultorios/guardar");
        return "dashboard/CRUDs/FormConsultorio";
    }

    @PostMapping("/consultorios/guardar")
    public String guardarConsultorio(@ModelAttribute Consultorio consultorio) {

        serviceConsultorio.crearConsultorio(consultorio);
        return "redirect:/consultorios";

    }

    @GetMapping("/consultorios/editar/{id}")
    public String editarConsultorio(@PathVariable Long id, Model model) {

        Consultorio consultorio = serviceConsultorio.obtenerConsultorio(id);

        model.addAttribute("consultorio", consultorio);
        model.addAttribute("title", "Editar Consultorio");
        model.addAttribute("accion", "/consultorios/actualizar/"+id);

        return "dashboard/CRUDs/FormConsultorio";
    }

    @PostMapping("/consultorios/actualizar/{id}")
    public String actualizarConsultorio(@PathVariable Long id,@ModelAttribute Consultorio consultorio) {
        serviceConsultorio.actualizarConsultorio(id,consultorio);
        return "redirect:/consultorios";
    }

    @GetMapping("/consultorios/baja/{id}")
    public String darBajaConsultorio(@PathVariable Long id){
        serviceConsultorio.darBajaConsultorio(id);
        return "redirect:/consultorios";
    }

    @GetMapping("/consultorios/eliminar/{id}")
    public String eliminarConsultorio(@PathVariable Long id){
        serviceConsultorio.eliminarConsultorio(id);
        return "redirect:/consultorios";
    }
}
