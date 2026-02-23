package com.example.Sistema_Clinica_Spring.Services.Programacion;

import com.example.Sistema_Clinica_Spring.Models.Programacion.Programacion;
import com.example.Sistema_Clinica_Spring.Repository.Programacion.ProgramacionRepository;
import com.example.Sistema_Clinica_Spring.Services.Programacion.InterfaceService.ProgramacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceProgramacion implements ProgramacionService {
    @Autowired
    ProgramacionRepository programacionRepository;

    public List<Programacion> listarProgramaciones(){
        return programacionRepository.findAll();
    }

    public Programacion obtenerProgramacion(Long id_programacion){
        Optional<Programacion> programacion = programacionRepository.findById(id_programacion);
        return programacion.orElse(null);
    }

    public ResponseEntity<String> crearProgramacion(Programacion programacion){
        programacionRepository.save(programacion);
        return ResponseEntity.ok("Programacion creada exitosamente");
    }

    public ResponseEntity<String> actualizarProgramacion(Long id_programacion, Programacion programacion){
        Optional<Programacion> programacon_encontrada = programacionRepository.findById(id_programacion);

        if (programacon_encontrada.isPresent()){
            Programacion programacionActualizar = programacon_encontrada.get();
            programacionActualizar.setDia(programacion.getDia());
            programacionActualizar.setFecha(programacion.getFecha());
            programacionActualizar.setEstado(programacion.getEstado());

            programacionRepository.save(programacionActualizar);
            return ResponseEntity.ok("Programacion actualizada exitosamente");
        }

        return null;
    }

    public ResponseEntity<String> eliminarProgramacion(Long id_programacion){
        programacionRepository.deleteById(id_programacion);
        return ResponseEntity.ok("Programacion eliminada exitosamente");
    }
}
