package com.example.Sistema_Clinica_Spring.Routes.Usuarios;

import com.example.Sistema_Clinica_Spring.Models.Usuarios.Trabajador;
import com.example.Sistema_Clinica_Spring.Models.Usuarios.Tipo_trabajador;
import com.example.Sistema_Clinica_Spring.Models.Usuarios.Complemento.Especialidad;
import com.example.Sistema_Clinica_Spring.Services.Usuarios.ServiceTrabajador;
import com.example.Sistema_Clinica_Spring.Services.Usuarios.Complemento.ServiceEspecialidad;

import com.example.Sistema_Clinica_Spring.Services.Usuarios.Service_tipo_trabajador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class TrabajadorRoutes {

    @Autowired
    private ServiceTrabajador serviceTrabajador;

    @Autowired
    private Service_tipo_trabajador serviceTipoTrabajador;

    @Autowired
    private ServiceEspecialidad serviceEspecialidad;

    @GetMapping("/trabajadores")
    public String listarTrabajadores(Model model) {

        model.addAttribute("trabajadores", serviceTrabajador.listarTrabajadores());
        model.addAttribute("title", "Trabajadores");

        return "dashboard/Apartados/Trabajadores";
    }

    @GetMapping("/trabajadores/nuevo")
    public String nuevoTrabajador(Model model) {

        model.addAttribute("trabajador", new Trabajador());
        model.addAttribute("tipos", serviceTipoTrabajador.listarTipoTrabajadores());
        model.addAttribute("especialidades", serviceEspecialidad.listarEspecialidades());

        model.addAttribute("accion", "/trabajadores/guardar");
        model.addAttribute("titulo", "Crear Trabajador");
        model.addAttribute("botonTexto", "Guardar");

        return "dashboard/CRUDs/FormTrabajador";
    }

    @GetMapping("/trabajadores/editar/{id}")
    public String editarTrabajador(@PathVariable Long id, Model model) {

        Trabajador trabajador = serviceTrabajador.obtenerTrabajador(id);

        model.addAttribute("trabajador", trabajador);
        model.addAttribute("tipos", serviceTipoTrabajador.listarTipoTrabajadores());
        model.addAttribute("especialidades", serviceEspecialidad.listarEspecialidades());

        model.addAttribute("accion", "/trabajadores/actualizar/" + id);
        model.addAttribute("titulo", "Editar Trabajador");
        model.addAttribute("botonTexto", "Actualizar");

        return "dashboard/CRUDs/FormTrabajador";
    }

    @PostMapping("/trabajadores/guardar")
    public String guardarTrabajador(@ModelAttribute Trabajador trabajador) {

        trabajador.setCreatedAt(LocalDateTime.now());
        trabajador.setEstado(1);

        serviceTrabajador.crearTrabajador(trabajador);

        return "redirect:/trabajadores";
    }


    @PostMapping("/trabajadores/actualizar/{id}")
    public String actualizarTrabajador(@PathVariable Long id,
                                       @ModelAttribute Trabajador trabajador) {

        serviceTrabajador.actualizarTrabajador(id, trabajador);

        return "redirect:/trabajadores";
    }

    @GetMapping("/trabajadores/baja/{id}")
    public String bajaTrabajador(@PathVariable Long id) {

        Trabajador t = serviceTrabajador.obtenerTrabajador(id);
        t.setEstado(0);
        serviceTrabajador.actualizarTrabajador(id, t);

        return "redirect:/trabajadores";
    }

    @GetMapping("/trabajadores/eliminar/{id}")
    public String eliminarTrabajador(@PathVariable Long id) {

        serviceTrabajador.eliminarTrabajador(id);

        return "redirect:/trabajadores";
    }
}