package com.example.Sistema_Clinica_Spring.Services.Transacciones.InterfaceService;

import com.example.Sistema_Clinica_Spring.Models.Transacciones.Examen;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ExamenService {
    public List<Examen> listarExamenesMedico();
    public Examen obtenerExamenMedico(Long id_examen);
    public ResponseEntity<String> crearExamenMedico(Examen examen);
    public ResponseEntity<String> actualizarExamenMedico(Long id_examen,Examen examen);
    public ResponseEntity<String> eliminarExamenMedico(Long id_examen);
}
