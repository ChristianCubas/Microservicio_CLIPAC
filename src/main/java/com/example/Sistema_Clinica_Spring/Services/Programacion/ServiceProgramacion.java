package com.example.Sistema_Clinica_Spring.Services.Programacion;

import com.example.Sistema_Clinica_Spring.Models.Programacion.Consultorio;
import com.example.Sistema_Clinica_Spring.Models.Programacion.Horario;
import com.example.Sistema_Clinica_Spring.Models.Programacion.Programacion;
import com.example.Sistema_Clinica_Spring.Models.Usuarios.Trabajador;
import com.example.Sistema_Clinica_Spring.Repository.Programacion.ConsultorioRepository;
import com.example.Sistema_Clinica_Spring.Repository.Programacion.HorarioRepository;
import com.example.Sistema_Clinica_Spring.Repository.Programacion.ProgramacionRepository;
import com.example.Sistema_Clinica_Spring.Repository.Usuarios.TrabajadorRepository;
import com.example.Sistema_Clinica_Spring.Services.Programacion.InterfaceService.ProgramacionService;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceProgramacion implements ProgramacionService {
    @Autowired
    ProgramacionRepository programacionRepository;

    @Autowired
    TrabajadorRepository trabajadorRepository;

    @Autowired
    ConsultorioRepository consultorioRepository;

    @Autowired
    HorarioRepository horarioRepository;

    public List<Programacion> listarProgramaciones(){
        return programacionRepository.findAll();
    }

    public Programacion obtenerProgramacion(Long id_programacion){
        Optional<Programacion> programacion = programacionRepository.findById(id_programacion);
        return programacion.orElse(null);
    }

    public ResponseEntity<String> crearProgramacion(Programacion programacion){
        programacionRepository.save(programacion);
        return ResponseEntity.ok("Programacion creada exitosamente");
    }

    public ResponseEntity<String> actualizarProgramacion(Long id_programacion, Programacion programacion){
        Optional<Programacion> programacion_encontrada = programacionRepository.findById(id_programacion);

        if (programacion_encontrada.isPresent()){
            Programacion programacionActualizar = programacion_encontrada.get();
            programacionActualizar.setDia(programacion.getDia());
            programacionActualizar.setFecha(programacion.getFecha());
            programacionActualizar.setEstado(programacion.getEstado());
            programacionActualizar.setHorario(programacion.getHorario());

            programacionRepository.save(programacionActualizar);
            return ResponseEntity.ok("Programacion actualizada exitosamente");
        }

        return null;
    }

    public ResponseEntity<String> eliminarProgramacion(Long id_programacion){
        programacionRepository.deleteById(id_programacion);
        return ResponseEntity.ok("Programacion eliminada exitosamente");
    }

    public List<Programacion> filtrarSemana(Long especialidadId, Long trabajadorId, Long consultorioId) {

        LocalDate hoy = LocalDate.now();
        LocalDate inicioSemana = hoy.with(DayOfWeek.MONDAY);
        LocalDate finSemana = inicioSemana.plusDays(6);

        return programacionRepository.filtrar(
                inicioSemana,
                finSemana,
                trabajadorId,
                consultorioId,
                especialidadId
        );
    }

    public ResponseEntity<String> crearProgramacion(
            Long trabajadorId,
            Long consultorioId,
            Long horarioId,
            LocalDate fecha,
            Integer dia,
            Integer estado) {

        List<Programacion> cruces = programacionRepository.validarCruce(
                dia, horarioId, trabajadorId, consultorioId);

        if (!cruces.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Conflicto: trabajador o consultorio ya ocupado en ese horario");
        }

        Trabajador trabajador = trabajadorRepository.findById(trabajadorId).orElseThrow();
        Consultorio consultorio = consultorioRepository.findById(consultorioId).orElseThrow();
        Horario horario = horarioRepository.findById(horarioId).orElseThrow();

        Programacion programacion = new Programacion();
        programacion.setTrabajador(trabajador);
        programacion.setConsultorio(consultorio);
        programacion.setHorario(horario);
        programacion.setFecha(fecha);
        programacion.setDia(dia);
        programacion.setEstado(estado);

        programacionRepository.save(programacion);

        return ResponseEntity.ok("Programación creada correctamente");
    }
}