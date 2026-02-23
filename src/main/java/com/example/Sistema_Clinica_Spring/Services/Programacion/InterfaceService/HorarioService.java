package com.example.Sistema_Clinica_Spring.Services.Programacion.InterfaceService;

import com.example.Sistema_Clinica_Spring.Models.Programacion.Horario;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HorarioService {
    public List<Horario> listarHorarios();
    public Horario obtenerHorario(Long id_horario);
    public ResponseEntity<String> crearHorario(Horario horario);
    public ResponseEntity<String> actualizarHorario(Long id_horario, Horario horario);
    public ResponseEntity<String> eliminarHorario(Long id_horario);
}
