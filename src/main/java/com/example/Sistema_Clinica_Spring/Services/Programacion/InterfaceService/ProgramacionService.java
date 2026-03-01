package com.example.Sistema_Clinica_Spring.Services.Programacion.InterfaceService;

import com.example.Sistema_Clinica_Spring.Models.Programacion.Programacion;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ProgramacionService {
    public List<Programacion> listarProgramaciones();
    public Programacion obtenerProgramacion(Long id_programacion);
    public ResponseEntity<String> crearProgramacion(Programacion programacion);
    public ResponseEntity<String> actualizarProgramacion(Long id_programacion, Programacion programacion);
    public ResponseEntity<String> eliminarProgramacion(Long id_programacion);
    public List<Programacion> filtrarSemana(Long especialidadId, Long trabajadorId, Long consultorioId);
    public ResponseEntity<String> crearProgramacion(Long trabajadorId, Long consultorioId, Long horarioId, LocalDate fecha, Integer dia, Integer estado);
}
