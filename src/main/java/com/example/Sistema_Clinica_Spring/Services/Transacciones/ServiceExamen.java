package com.example.Sistema_Clinica_Spring.Services.Transacciones;

import com.example.Sistema_Clinica_Spring.Models.Transacciones.Examen;
import com.example.Sistema_Clinica_Spring.Repository.Transacciones.ExamenRepository;
import com.example.Sistema_Clinica_Spring.Services.Transacciones.InterfaceService.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceExamen implements ExamenService {
    @Autowired
    ExamenRepository examenRepository;

    public List<Examen> listarExamenesMedico(){
        return examenRepository.findAll();
    }

    public Examen obtenerExamenMedico(Long id_examen){
        Optional<Examen> examen = examenRepository.findById(id_examen);
        return examen.orElse(null);
    }

    public ResponseEntity<String> crearExamenMedico(Examen examen){
        examenRepository.save(examen);
        return ResponseEntity.ok("Examen registrado exitosamente");
    }

    public ResponseEntity<String> actualizarExamenMedico(Long id_examen,Examen examen){
        Optional<Examen> examenEncontrado = examenRepository.findById(id_examen);

        if (examenEncontrado.isPresent()){
            Examen exam = examenEncontrado.get();
            exam.setTipoExamen(examen.getTipoExamen());
            exam.setTitulo(examen.getTitulo());
            exam.setDescripcion(examen.getDescripcion());
            exam.setTransaccion(examen.getTransaccion());

            examenRepository.save(examen);
            return ResponseEntity.ok("Examen medico actualizado exitosamente");
        }

        return null;
    }

    public ResponseEntity<String> eliminarExamenMedico(Long id_examen){
        examenRepository.deleteById(id_examen);
        return ResponseEntity.ok("Examen medico eliminado exitosamente");
    }
}
