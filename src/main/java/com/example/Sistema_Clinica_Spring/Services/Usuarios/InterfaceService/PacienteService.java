package com.example.Sistema_Clinica_Spring.Services.Usuarios.InterfaceService;

import com.example.Sistema_Clinica_Spring.Models.Usuarios.Paciente;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PacienteService {
    public List<Paciente> listarPacientes();
    public Paciente obtenerPaciente(Long id_paciente);
    public ResponseEntity<String> crearPaciente(Paciente paciente);
    public ResponseEntity<String> actualizarPaciente(Long id_paciente, Paciente paciente);
    public ResponseEntity<String> eliminarPaciente(Long id_paciente);
}
