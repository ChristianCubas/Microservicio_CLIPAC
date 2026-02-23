package com.example.Sistema_Clinica_Spring.Services.Usuarios.Complemento;

import com.example.Sistema_Clinica_Spring.Models.Usuarios.Complemento.Especialidad;
import com.example.Sistema_Clinica_Spring.Repository.Usuarios.Complemento.EspecialidadRepository;
import com.example.Sistema_Clinica_Spring.Services.Usuarios.InterfaceService.EspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceEspecialidad implements EspecialidadService {

    @Autowired
    EspecialidadRepository especialidadRepository;

    public List<Especialidad> listarEspecialidades(){
        return especialidadRepository.findAll();
    }

    public Especialidad obtenerEspecialidad(long id_especialidad){
        Optional<Especialidad> especialidad_optional = especialidadRepository.findById(id_especialidad);
        return especialidad_optional.orElse(null);
    }

    public ResponseEntity<String> crearEspecialidad(Especialidad especialidad){
        especialidadRepository.save(especialidad);
        return ResponseEntity.ok("Especialidad registrada");
    }

    public ResponseEntity<String> actualizarEspecialidad(Long id_especialidad,Especialidad cambios){
        Optional<Especialidad> especialidad_recibida = especialidadRepository.findById(id_especialidad);
        if (especialidad_recibida.isPresent()){
            Especialidad especialidad = especialidad_recibida.get();
            especialidad.setNombre(cambios.getNombre());
            especialidad.setEstado(cambios.getEstado());
            especialidadRepository.save(especialidad);
            return ResponseEntity.ok("Especialidad actualizada");
        }
        return null;
    }

    public ResponseEntity<String> eliminarEspecialidad(Long id_especialidad){
        especialidadRepository.deleteById(id_especialidad);
        return ResponseEntity.ok("Especialidad eliminada");
    }
}
