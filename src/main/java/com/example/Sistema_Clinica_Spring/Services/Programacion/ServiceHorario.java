package com.example.Sistema_Clinica_Spring.Services.Programacion;

import com.example.Sistema_Clinica_Spring.Models.Programacion.Horario;
import com.example.Sistema_Clinica_Spring.Repository.Programacion.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceHorario {
    @Autowired
    HorarioRepository horarioRepository;

    public List<Horario> listarHorarios(){
        return horarioRepository.findAll();
    }

    public Horario obtenerHorario(Long id_horario){
        Optional<Horario> horario_encontrado = horarioRepository.findById(id_horario);
        return horario_encontrado.orElse(null);
    }

    public ResponseEntity<String> crearHorario(Horario horario){
        horarioRepository.save(horario);
        return ResponseEntity.ok("Horario creado exitosamente");
    }

    public ResponseEntity<String> actualizarHorario(Long id_horario, Horario horario){
        Optional<Horario> horario_encontrado = horarioRepository.findById(id_horario);

        if (horario_encontrado.isPresent()){
            Horario horarioActualizar = horario_encontrado.get();
            horarioActualizar.setHora_inicio(horario.getHora_inicio());
            horarioActualizar.setHora_fin(horario.getHora_fin());
            horarioActualizar.setEstado(horario.getEstado());

            horarioRepository.save(horarioActualizar);
            return ResponseEntity.ok("Horario actualizado exitosamente");
        }

        return null;
    }

    public ResponseEntity<String> eliminarHorario(Long id_horario){
        horarioRepository.deleteById(id_horario);
        return ResponseEntity.ok("Horario eliminado exitosamente");
    }

}



