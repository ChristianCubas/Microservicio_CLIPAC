package com.example.Sistema_Clinica_Spring.Services.Transacciones.InterfaceService;

import com.example.Sistema_Clinica_Spring.Models.Transacciones.Complemento.Tipo_examen;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface Tipo_examenService {
    public List<Tipo_examen> listarTiposExamenes();
    public Tipo_examen obtenerTipoExamen(Long id_tipo_examen);
    public ResponseEntity<String> crearTipoExamen(Tipo_examen tipoExamen);
    public ResponseEntity<String> actualizarTipoExamen(Long id_tipo_examen,Tipo_examen tipoExamen);
    public ResponseEntity<String> eliminarTipoExamen(Long id_tipo_examen);
}
