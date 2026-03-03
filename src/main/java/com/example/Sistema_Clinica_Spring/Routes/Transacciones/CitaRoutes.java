package com.example.Sistema_Clinica_Spring.Routes.Transacciones;

import com.example.Sistema_Clinica_Spring.Models.Programacion.Programacion;
import com.example.Sistema_Clinica_Spring.Models.Transacciones.Cita;
import com.example.Sistema_Clinica_Spring.Models.Transacciones.Transaccion;
import com.example.Sistema_Clinica_Spring.Models.Usuarios.Paciente;
import com.example.Sistema_Clinica_Spring.Services.Programacion.ServiceProgramacion;
import com.example.Sistema_Clinica_Spring.Services.Transacciones.ServiceCita;
import com.example.Sistema_Clinica_Spring.Services.Transacciones.ServiceTransaccion;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/citas")
public class CitaRoutes {

    @Autowired
    private ServiceCita serviceCita;

    @Autowired
    private ServiceTransaccion serviceTransaccion;

    @Autowired
    private ServiceProgramacion serviceProgramacion;

    @GetMapping("/nuevo")
    public String registrarCita(Model model, HttpSession sesion) {
        Paciente paciente = (Paciente) sesion.getAttribute("paciente");
        if (paciente == null) {
            return "redirect:/iniciarSesion";
        }

        // Datos para los filtros progresivos
        model.addAttribute("horariosDisponibles",
                serviceCita.obtenerHorariosDisponiblesPorEspecialidad());

        return "registrarCita"; // tu HTML → src/main/resources/templates/registrarCita.html
    }

    @PostMapping("/guardar")
    public String guardarCita(
            @RequestParam("trabajadorId") Long trabajadorId,
            @RequestParam("programacionId") Long programacionId,
            @RequestParam("modalidad") Integer modalidad,
            HttpSession sesion,
            RedirectAttributes redirectAttrs
    ) {
        try {
            Paciente paciente = (Paciente) sesion.getAttribute("paciente");
            if (paciente == null) {
                return "redirect:/iniciarSesion";
            }

            // 1. Crear la transacción
            Transaccion transaccion = new Transaccion();
            transaccion.setTipo("CITA");
            transaccion.setEstado(1);
            transaccion.setIdPaciente(paciente.getId_paciente());
            transaccion.setTotal(BigDecimal.valueOf(50.00));
            transaccion.setCreatedAt(LocalDateTime.now());
            transaccion.setFecha(LocalDate.now());
            transaccion.setHora(LocalDateTime.now().toLocalTime());

            Transaccion transaccionGuardada = serviceTransaccion.crearTransaccion(transaccion);

            // 2. Obtener la programación (y su horario, si vas a usarlo)
            Programacion programacion = serviceProgramacion.obtenerProgramacion(programacionId);

            // 3. Crear la cita
            Cita cita = new Cita();
            cita.setTransaccion(transaccionGuardada);
            cita.setProgramacion(programacion);
            cita.setModalidad(modalidad);
            cita.setEstado(1);

            serviceCita.crearCita(cita);

            redirectAttrs.addFlashAttribute("mensaje",
                    "¡Cita reservada exitosamente para " + programacion.getFecha() + " a las horas de tu horario!");

            return "redirect:/citas/exito";

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttrs.addFlashAttribute("error",
                    "Error al reservar: " + e.getMessage());
            return "redirect:/citas/nuevo";
        }
    }

    @GetMapping("/exito")
    public String exitoCita(Model model) {
        return "exitoCita";
    }
}
