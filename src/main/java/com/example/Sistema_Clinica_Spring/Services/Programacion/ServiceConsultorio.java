package com.example.Sistema_Clinica_Spring.Services.Programacion;

import com.example.Sistema_Clinica_Spring.Models.Programacion.Consultorio;
import com.example.Sistema_Clinica_Spring.Repository.Programacion.ConsultorioRepository;
import com.example.Sistema_Clinica_Spring.Services.Programacion.InterfaceService.ConsultorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceConsultorio implements ConsultorioService {

    @Autowired
    ConsultorioRepository consultorioRepository;

    public List<Consultorio> listarConsultorios(){
        return consultorioRepository.findAll();
    }

    public Consultorio obtenerConsultorio(Long id_consultorio){
        Optional<Consultorio> consultorio = consultorioRepository.findById(id_consultorio);
        return consultorio.orElse(null);
    }

    public ResponseEntity<String> crearConsultorio(Consultorio consultorio){
        consultorioRepository.save(consultorio);
        return ResponseEntity.ok("Consultorio registrado exitosamente");
    }

    public ResponseEntity<String> actualizarConsultorio(Long id_consultorio, Consultorio consultorio){
        Optional<Consultorio> consultorio_obtenido = consultorioRepository.findById(id_consultorio);

        if (consultorio_obtenido.isPresent()){
            Consultorio consultorioActualizar = consultorio_obtenido.get();

            consultorioActualizar.setEstado(consultorio.getEstado());
            consultorioActualizar.setPiso(consultorio.getPiso());
            consultorioActualizar.setProgramaciones(consultorio.getProgramaciones());
            consultorioActualizar.setNombre(consultorio.getNombre());

            consultorioRepository.save(consultorioActualizar);
            return ResponseEntity.ok("Consultorio actualizado exitosamente");
        }

        return null;
    }

    public ResponseEntity<String> darBajaConsultorio(Long id_consultorio){
        Optional<Consultorio> consultorio_obtenido = consultorioRepository.findById(id_consultorio);

        if (consultorio_obtenido.isPresent()){
            Consultorio consultorioActualizar = consultorio_obtenido.get();

            consultorioActualizar.setEstado(0);

            consultorioRepository.save(consultorioActualizar);
            return ResponseEntity.ok("Consultorio actualizado exitosamente");
        }

        return null;
    }

    public ResponseEntity<String> eliminarConsultorio(Long id_consultorio){
        consultorioRepository.deleteById(id_consultorio);
        return ResponseEntity.ok("Consultorio eliminado exitosamente");
    }
}
