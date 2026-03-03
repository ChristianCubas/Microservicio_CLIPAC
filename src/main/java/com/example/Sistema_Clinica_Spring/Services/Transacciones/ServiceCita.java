package com.example.Sistema_Clinica_Spring.Services.Transacciones;

import com.example.Sistema_Clinica_Spring.Models.Programacion.Programacion;
import com.example.Sistema_Clinica_Spring.Models.Transacciones.Cita;
import com.example.Sistema_Clinica_Spring.Repository.Programacion.ProgramacionRepository;
import com.example.Sistema_Clinica_Spring.Repository.Transacciones.CitaRepository;
import com.example.Sistema_Clinica_Spring.Services.Transacciones.InterfaceService.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceCita implements CitaService {
    @Autowired
    CitaRepository citaRepository;

    @Autowired
    ProgramacionRepository programacionRepository;

    public List<Cita> listarCitas(){
        return citaRepository.findAll();
    }

    public Cita obtenerCita(Long id_cita){
        Optional<Cita> citaEncontrada = citaRepository.findById(id_cita);
        return citaEncontrada.orElse(null);
    }

    public ResponseEntity<String> actualizarCita(Long id_cita,Cita cita){
        Optional<Cita> citaEncontrada = citaRepository.findById(id_cita);

        if (citaEncontrada.isPresent()){
            Cita citaActualizar = citaEncontrada.get();

            citaActualizar.setTransaccion(cita.getTransaccion());
            citaActualizar.setModalidad(cita.getModalidad());
            citaActualizar.setEstado(cita.getEstado());

            citaRepository.save(citaActualizar);

            return ResponseEntity.ok("Cita actualizada exitosamente");
        }
        return null;
    }

    public ResponseEntity<String> eliminarCita(Long id_cita){
        citaRepository.deleteById(id_cita);
        return ResponseEntity.ok("Cita eliminada exitosamente");
    }

    public boolean tieneDisponibilidad(Long idProgramacion) {
        return citaRepository.countByProgramacionIdAndEstado(idProgramacion, 1) == 0;
    }

    public ResponseEntity<String> crearCita(Cita cita) {
        if (!tieneDisponibilidad(cita.getProgramacion().getId_programacion())) {
            return ResponseEntity.badRequest().body("Horario ocupado");
        }

        Programacion programacion = programacionRepository.findById(cita.getProgramacion().getId_programacion()).get();
        programacion.setEstado(0);
        programacionRepository.save(programacion);

        citaRepository.save(cita);
        return ResponseEntity.ok("Cita creada exitosamente");
    }

    public List<Object[]> obtenerHorariosDisponiblesPorEspecialidad() {
        return citaRepository.obtenerHorariosDisponiblesPorEspecialidad();
    }

    public List<Cita> obtenerCitasPorPaciente(Long idPaciente) {
        return citaRepository.findByTransaccionIdPacienteOrderByCreatedAtDesc(idPaciente);
    }

}
