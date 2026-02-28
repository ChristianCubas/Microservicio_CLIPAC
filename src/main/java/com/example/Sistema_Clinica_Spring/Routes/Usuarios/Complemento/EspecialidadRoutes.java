package com.example.Sistema_Clinica_Spring.Routes.Usuarios.Complemento;

import com.example.Sistema_Clinica_Spring.Models.Usuarios.Complemento.Especialidad;
import com.example.Sistema_Clinica_Spring.Services.Usuarios.Complemento.ServiceEspecialidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EspecialidadRoutes {

    @Autowired
    ServiceEspecialidad serviceEspecialidad;

    // LISTAR
    @GetMapping("/especialidades")
    public String listarEspecialidades(Model model) {
        List<Especialidad> listado = serviceEspecialidad.listarEspecialidades();
        model.addAttribute("especialidades", listado);
        model.addAttribute("title", "Especialidades");
        return "dashboard/Apartados/Especialidades";
    }

    // FORM NUEVO
    @GetMapping("/especialidad/nuevo")
    public String nuevaEspecialidad(Model model) {
        model.addAttribute("especialidad", new Especialidad());
        model.addAttribute("accion","/especialidad/guardar");
        model.addAttribute("title", "Nueva Especialidad");
        return "dashboard/CRUDs/FormEspecialidad";
    }

    // GUARDAR
    @PostMapping("/especialidad/guardar")
    public String guardarEspecialidad(@ModelAttribute Especialidad especialidad) {
        serviceEspecialidad.crearEspecialidad(especialidad);
        return "redirect:/especialidades";
    }

    // EDITAR
    @GetMapping("/especialidad/editar/{id}")
    public String editarEspecialidad(@PathVariable Long id, Model model) {
        Especialidad especialidad = serviceEspecialidad.obtenerEspecialidad(id);
        model.addAttribute("especialidad", especialidad);
        model.addAttribute("accion","/especialidad/actualizar/"+id);
        model.addAttribute("title", "Editar Especialidad");
        return "dashboard/CRUDs/FormEspecialidad";
    }

    @PostMapping("/especialidad/actualizar/{id}")
    public String actualizarEspecialidad(@PathVariable Long id, @ModelAttribute Especialidad especialidad){
        serviceEspecialidad.actualizarEspecialidad(id,especialidad);
        return "redirect:/especialidades";
    }

    // BAJA LOGICA
    @GetMapping("/especialidad/baja/{id}")
    public String bajaEspecialidad(@PathVariable Long id) {
        serviceEspecialidad.darBajaEspecialidad(id);
        return "redirect:/especialidades";
    }

    // ELIMINAR
    @GetMapping("/especialidad/eliminar/{id}")
    public String eliminarEspecialidad(@PathVariable Long id) {
        serviceEspecialidad.eliminarEspecialidad(id);
        return "redirect:/especialidades";
    }
}