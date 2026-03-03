package com.example.Sistema_Clinica_Spring.Routes.Programacion;

import com.example.Sistema_Clinica_Spring.Models.Programacion.Programacion;
import com.example.Sistema_Clinica_Spring.Services.Programacion.ServiceConsultorio;
import com.example.Sistema_Clinica_Spring.Services.Programacion.ServiceHorario;
import com.example.Sistema_Clinica_Spring.Services.Programacion.ServiceProgramacion;
import com.example.Sistema_Clinica_Spring.Services.Usuarios.Complemento.ServiceEspecialidad;
import com.example.Sistema_Clinica_Spring.Services.Usuarios.ServiceTrabajador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/programaciones")
public class ProgramacionRoutes {

    @Autowired
    private ServiceProgramacion serviceProgramacion;

    @Autowired
    private ServiceConsultorio serviceConsultorio;

    @Autowired
    private ServiceHorario serviceHorario;

    @Autowired
    private ServiceTrabajador serviceTrabajador;

    @Autowired
    private ServiceEspecialidad serviceEspecialidad;

    @GetMapping
    public String vistaProgramacionSemanal(Model model) {

        model.addAttribute("especialidades", serviceEspecialidad.listarEspecialidades());
        model.addAttribute("trabajadores", serviceTrabajador.listarTrabajadores());
        model.addAttribute("consultorios", serviceConsultorio.listarConsultorios());
        model.addAttribute("horarios", serviceHorario.listarHorarios());
        model.addAttribute("programaciones", serviceProgramacion.filtrarSemana(null,null,null));

        model.addAttribute("title", "Programación Semanal");

        return "dashboard/Apartados/Programaciones";
    }

    @GetMapping("/filtrar")
    public String filtrarProgramacion(
            @RequestParam(required = false) Long especialidadId,
            @RequestParam(required = false) Long trabajadorId,
            @RequestParam(required = false) Long consultorioId,
            Model model) {

        List<Programacion> lista = serviceProgramacion.filtrarSemana(
                especialidadId, trabajadorId, consultorioId);

        model.addAttribute("especialidades", serviceEspecialidad.listarEspecialidades());
        model.addAttribute("trabajadores", serviceTrabajador.listarTrabajadores());
        model.addAttribute("consultorios", serviceConsultorio.listarConsultorios());
        model.addAttribute("horarios", serviceHorario.listarHorarios());
        model.addAttribute("programaciones", lista);

        model.addAttribute("title", "Programación Semanal");

        return "dashboard/Apartados/Programaciones";
    }

    @GetMapping("/nueva")
    public String nuevaProgramacion(Model model) {

        model.addAttribute("titulo","Nueva programacion");
        model.addAttribute("accion","/programaciones/guardar");
        model.addAttribute("programacion",new Programacion());
        model.addAttribute("trabajadores", serviceTrabajador.listarTrabajadores());
        model.addAttribute("consultorios", serviceConsultorio.listarConsultorios());
        model.addAttribute("horarios", serviceHorario.listarHorarios());

        return "dashboard/CRUDs/FormProgramacion";
    }

    @PostMapping("/guardar")
    public String guardarProgramacion(
            @ModelAttribute Programacion programacion,
            RedirectAttributes redirectAttributes) {

        serviceProgramacion.crearProgramacion(programacion);

        redirectAttributes.addFlashAttribute("success", "Programación guardada correctamente");
        return "redirect:/programaciones";
    }

    @GetMapping("/editar/{id}")
    public String editarProgramacion(@PathVariable Long id, Model model){
        Programacion programacion = serviceProgramacion.obtenerProgramacion(id);

        model.addAttribute("programacion",programacion);
        model.addAttribute("titulo","Editar programacion");
        model.addAttribute("accion","/programaciones/actualizar/"+id);
        model.addAttribute("trabajadores", serviceTrabajador.listarTrabajadores());
        model.addAttribute("consultorios", serviceConsultorio.listarConsultorios());
        model.addAttribute("horarios", serviceHorario.listarHorarios());

        return "dashboard/CRUDs/FormProgramacion";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarProgramacion(@PathVariable Long id, @ModelAttribute Programacion programacion){
        serviceProgramacion.actualizarProgramacion(id,programacion);
        return "redirect:/programaciones";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProgramacion(@PathVariable Long id) {
        serviceProgramacion.eliminarProgramacion(id);
        return "redirect:/programaciones";
    }
}