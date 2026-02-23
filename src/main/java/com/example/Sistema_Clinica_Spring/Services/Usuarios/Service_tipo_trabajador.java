package com.example.Sistema_Clinica_Spring.Services.Usuarios;

import com.example.Sistema_Clinica_Spring.Models.Usuarios.Tipo_trabajador;
import com.example.Sistema_Clinica_Spring.Repository.Usuarios.Tipo_trabajadorRepository;
import com.example.Sistema_Clinica_Spring.Services.Usuarios.InterfaceService.Tipo_trabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Service_tipo_trabajador implements Tipo_trabajadorService {
    @Autowired
    Tipo_trabajadorRepository tipoTrabajadorRepository;

    public List<Tipo_trabajador> listarTipoTrabajadores() {
        return tipoTrabajadorRepository.findAll();
    }

    public Tipo_trabajador obtenerTipoTrabajador(Long id_tipo_trabajador) {
        Optional<Tipo_trabajador> tipo_trabajador = tipoTrabajadorRepository.findById(id_tipo_trabajador);
        return tipo_trabajador.orElse(null);
    }

    public ResponseEntity<String> crearTipoTrabajador(Tipo_trabajador tipo_trabajador) {
        tipoTrabajadorRepository.save(tipo_trabajador);
        return ResponseEntity.ok("tipo trabajador creado exitosamente");
    }

    public ResponseEntity<String> actualizarTipoTrabajador(Long id_tipo_trabajador, Tipo_trabajador tipo_trabajador){
        Optional<Tipo_trabajador> tipo_trabajador_actualizar = tipoTrabajadorRepository.findById(id_tipo_trabajador);

        if (tipo_trabajador_actualizar.isPresent()){
            Tipo_trabajador tiptrabajador = tipo_trabajador_actualizar.get();
            tiptrabajador.setNombre(tipo_trabajador.getNombre());
            tiptrabajador.setEstado(tipo_trabajador.getEstado());
            tipoTrabajadorRepository.save(tiptrabajador);
            return ResponseEntity.ok("Tipo trabajador actualizado");
        }

        return null;
    }

    public ResponseEntity<String> eliminarTipoTrabajador(Long id_tipo_trabajador){
        tipoTrabajadorRepository.deleteById(id_tipo_trabajador);
        return ResponseEntity.ok("Tipo trabajador eliminado exitosamente");
    }
}
