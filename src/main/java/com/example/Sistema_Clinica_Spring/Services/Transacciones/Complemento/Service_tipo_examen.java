package com.example.Sistema_Clinica_Spring.Services.Transacciones.Complemento;

import com.example.Sistema_Clinica_Spring.Models.Transacciones.Complemento.Tipo_examen;
import com.example.Sistema_Clinica_Spring.Repository.Transacciones.Complemento.Tipo_examenRepository;
import com.example.Sistema_Clinica_Spring.Services.Transacciones.InterfaceService.Tipo_examenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Service_tipo_examen implements Tipo_examenService {
    @Autowired
    Tipo_examenRepository tipoExamenRepository;

    public List<Tipo_examen> listarTiposExamenes(){
        return tipoExamenRepository.findAll();
    }

    public Tipo_examen obtenerTipoExamen(Long id_tipo_examen){
        Optional<Tipo_examen> tipoExamen = tipoExamenRepository.findById(id_tipo_examen);
        return tipoExamen.orElse(null);
    }

    public ResponseEntity<String> crearTipoExamen(Tipo_examen tipoExamen){
        tipoExamenRepository.save(tipoExamen);
        return ResponseEntity.ok("Tipo examen creado exitosamente");
    }

    public ResponseEntity<String> actualizarTipoExamen(Long id_tipo_examen,Tipo_examen tipoExamen){
        Optional<Tipo_examen> tipoExamenRecibido = tipoExamenRepository.findById(id_tipo_examen);

        if (tipoExamenRecibido.isPresent()){
            Tipo_examen tipExam = tipoExamenRecibido.get();
            tipExam.setNombre(tipoExamen.getNombre());
            tipExam.setDescripcion(tipoExamen.getDescripcion());
            tipExam.setEstado(tipoExamen.getEstado());

            tipoExamenRepository.save(tipExam);
            return ResponseEntity.ok("Tipo examen creado exitosamente");
        }

        return null;
    }

    public ResponseEntity<String> eliminarTipoExamen(Long id_tipo_examen){
        tipoExamenRepository.deleteById(id_tipo_examen);
        return ResponseEntity.ok("Tipo examen eliminado exitosamente");
    }
}
