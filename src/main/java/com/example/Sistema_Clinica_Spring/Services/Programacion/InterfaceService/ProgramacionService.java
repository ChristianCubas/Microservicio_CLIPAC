package com.example.Sistema_Clinica_Spring.Services.Programacion.InterfaceService;

import com.example.Sistema_Clinica_Spring.Models.Programacion.Programacion;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProgramacionService {
    public List<Programacion> listarProgramaciones();
    public Programacion obtenerProgramacion(Long id_programacion);
    public ResponseEntity<String> crearProgramacion(Programacion programacion);
    public ResponseEntity<String> actualizarProgramacion(Long id_programacion, Programacion programacion);
    public ResponseEntity<String> eliminarProgramacion(Long id_programacion);
}
