package com.example.Sistema_Clinica_Spring.Services.Programacion.InterfaceService;

import com.example.Sistema_Clinica_Spring.Models.Programacion.Consultorio;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ConsultorioService {
    public List<Consultorio> listarConsultorios();
    public Consultorio obtenerConsultorio(Long id_consultorio);
    public ResponseEntity<String> crearConsultorio(Consultorio consultorio);
    public ResponseEntity<String> actualizarConsultorio(Long id_consultorio, Consultorio consultorio);
    public ResponseEntity<String> darBajaConsultorio(Long id_consultorio);
    public ResponseEntity<String> eliminarConsultorio(Long id_consultorio);
}
