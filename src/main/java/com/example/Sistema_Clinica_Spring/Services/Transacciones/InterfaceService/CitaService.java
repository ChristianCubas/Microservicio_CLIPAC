package com.example.Sistema_Clinica_Spring.Services.Transacciones.InterfaceService;

import com.example.Sistema_Clinica_Spring.Models.Transacciones.Cita;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CitaService {
    public List<Cita> listarCitas();
    public Cita obtenerCita(Long id_cita);
    public ResponseEntity<String> crearCita(Cita cita);
    public ResponseEntity<String> actualizarCita(Long id_cita,Cita cita);
    public ResponseEntity<String> eliminarCita(Long id_cita);
}
