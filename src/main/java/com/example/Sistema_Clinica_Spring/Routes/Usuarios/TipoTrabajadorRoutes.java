package com.example.Sistema_Clinica_Spring.Routes.Usuarios;

import com.example.Sistema_Clinica_Spring.Models.Usuarios.Tipo_trabajador;
import com.example.Sistema_Clinica_Spring.Services.Usuarios.Service_tipo_trabajador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TipoTrabajadorRoutes {
    @Autowired
    Service_tipo_trabajador serviceTipoTrabajador;

    @GetMapping("/tipos_trabajadores")
    public String listarTiposTrabajadores(Model model){
        List<Tipo_trabajador> tiposTrabajadores = serviceTipoTrabajador.listarTipoTrabajadores();
        model.addAttribute("tipos_trabajadores",tiposTrabajadores);
        return "dashboard/Apartados/Tipos_Trabajadores";
    }

    @GetMapping("/tipos-trabajador/nuevo")
    public String nuevo(Model model){

        model.addAttribute("tipoTrabajador", new Tipo_trabajador());
        model.addAttribute("accion","/tipos-trabajador/guardar");
        model.addAttribute("titulo","Crear Tipo de Trabajador");
        model.addAttribute("botonTexto","Guardar");

        return "dashboard/CRUDs/FormTipoTrabajador";
    }

    @GetMapping("/tipos-trabajador/baja/{id}")
    public String darbaja(@PathVariable Long id){
        serviceTipoTrabajador.darBajaTipoTrabajador(id);
        return "redirect:/tipos_trabajadores";
    }

    @GetMapping("/tipos-trabajador/editar/{id}")
    public String editar(@PathVariable Long id, Model model){

        model.addAttribute("accion","/tipos-trabajador/editar/"+id);
        Tipo_trabajador tipo = serviceTipoTrabajador.obtenerTipoTrabajador(id);

        model.addAttribute("tipoTrabajador", tipo);
        model.addAttribute("accion","/tipos-trabajador/actualizar/" + id);
        model.addAttribute("titulo","Editar Tipo de Trabajador");
        model.addAttribute("botonTexto","Actualizar");

        return "dashboard/CRUDs/FormTipoTrabajador";
    }

    @PostMapping("/tipos-trabajador/guardar")
    public String guardar(@ModelAttribute Tipo_trabajador tipo){
        serviceTipoTrabajador.crearTipoTrabajador(tipo);
        return "redirect:/tipos_trabajadores";
    }

    @PostMapping("/tipos-trabajador/actualizar/{id}")
    public String actualizar(@PathVariable Long id,
                             @ModelAttribute Tipo_trabajador tipo){
        serviceTipoTrabajador.actualizarTipoTrabajador(id, tipo);
        return "redirect:/tipos_trabajadores";
    }

    @GetMapping("/tipos-trabajador/eliminar/{id}")
    public String eliminar(@PathVariable Long id){
        serviceTipoTrabajador.eliminarTipoTrabajador(id);
        return "redirect:/tipos_trabajadores";
    }
}
