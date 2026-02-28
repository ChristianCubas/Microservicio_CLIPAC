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

    // =========================================
    // VISTA PRINCIPAL
    // =========================================
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

    // =========================================
    // FILTRAR
    // =========================================
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

    // =========================================
    // FORM NUEVA
    // =========================================
    @GetMapping("/nueva")
    public String nuevaProgramacion(Model model) {

        model.addAttribute("trabajadores", serviceTrabajador.listarTrabajadores());
        model.addAttribute("consultorios", serviceConsultorio.listarConsultorios());
        model.addAttribute("horarios", serviceHorario.listarHorarios());

        return "dashboard/CRUDs/FormProgramacion";
    }

    // =========================================
    // GUARDAR
    // =========================================
    @PostMapping("/guardar")
    public String guardarProgramacion(
            @RequestParam Long trabajadorId,
            @RequestParam Long consultorioId,
            @RequestParam Long horarioId,
            @RequestParam LocalDate fecha,
            @RequestParam Integer dia,
            @RequestParam Integer estado,
            RedirectAttributes redirectAttributes) {

        ResponseEntity<String> respuesta = serviceProgramacion.crearProgramacion(
                trabajadorId, consultorioId, horarioId, fecha, dia, estado);

        if (respuesta.getStatusCode().isError()) {
            redirectAttributes.addFlashAttribute("error", respuesta.getBody());
        } else {
            redirectAttributes.addFlashAttribute("success", respuesta.getBody());
        }

        return "redirect:/programaciones";
    }

    // =========================================
    // ELIMINAR
    // =========================================
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        serviceProgramacion.eliminarProgramacion(id);
        return "redirect:/programaciones";
    }
}