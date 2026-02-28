package com.example.Sistema_Clinica_Spring.Services.Usuarios;

import com.example.Sistema_Clinica_Spring.Models.Usuarios.Trabajador;
import com.example.Sistema_Clinica_Spring.Repository.Usuarios.TrabajadorRepository;
import com.example.Sistema_Clinica_Spring.Services.Usuarios.InterfaceService.TrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceTrabajador implements TrabajadorService {
    @Autowired
    TrabajadorRepository trabajadorRepository;

    public List<Trabajador> listarTrabajadores(){
        return trabajadorRepository.findAll();
    }

    public Trabajador obtenerTrabajador(Long id_trabajador){
        Optional<Trabajador> trabajador = trabajadorRepository.findById(id_trabajador);
        return trabajador.orElse(null);
    }

    public ResponseEntity<String> crearTrabajador(Trabajador trabajador) {
        trabajadorRepository.save(trabajador);
        return ResponseEntity.ok("Trabajador creado exitosamente");
    }

    public ResponseEntity<String> actualizarTrabajador(Long id_trabajador, Trabajador trabajador) {
        Optional<Trabajador> trabajador_recibido = trabajadorRepository.findById(id_trabajador);

        if (trabajador_recibido.isPresent()){
            Trabajador trabajador_actualizar = trabajador_recibido.get();
            trabajador_actualizar.setNombres(trabajador.getNombres());
            trabajador_actualizar.setApellidos(trabajador.getApellidos());
            trabajador_actualizar.setEmail(trabajador.getEmail());
            trabajador_actualizar.setTelefono(trabajador.getTelefono());
            trabajador_actualizar.setNroDocumento(trabajador.getNroDocumento());
            trabajador_actualizar.setDireccion(trabajador.getDireccion());
            trabajador_actualizar.setColegiatura(trabajador.getColegiatura());
            trabajador_actualizar.setTipoTrabajador(trabajador.getTipoTrabajador());
            trabajador_actualizar.setContrasenia(trabajador.getContrasenia());
            trabajador_actualizar.setEstado(trabajador.getEstado());

            trabajadorRepository.save(trabajador_actualizar);
            return ResponseEntity.ok("Trabajador actualizado correctamente");
        }

        return null;
    }

    public ResponseEntity<String> eliminarTrabajador(Long id_trabajador) {
        trabajadorRepository.deleteById(id_trabajador);
        return ResponseEntity.ok("Trabajador eliminado exitosamente");
    }

    public Trabajador logearTrabajador(String email,String contrasenia){
        for (Trabajador trabajador : trabajadorRepository.findAll()){
            if (trabajador.getEmail().equals(email) && trabajador.getContrasenia().equals(contrasenia)){
                return trabajador;
            }
        }

        return null;
    }

    public ResponseEntity<String> darBajaTrabajador(Long id){
        Optional<Trabajador> trabajador = trabajadorRepository.findById(id);
        if (trabajador.isPresent()){
            Trabajador trabajador_actualizar = trabajador.get();
            trabajador_actualizar.setEstado(0);
            trabajadorRepository.save(trabajador_actualizar);
            return ResponseEntity.ok("Trabajador dado de baja exitosamente");
        }

        return null;
    }
}
