package com.example.Sistema_Clinica_Spring.Services.Transacciones;

import com.example.Sistema_Clinica_Spring.Models.Transacciones.Cita;
import com.example.Sistema_Clinica_Spring.Models.Transacciones.Transaccion;
import com.example.Sistema_Clinica_Spring.Models.Usuarios.Paciente;
import com.example.Sistema_Clinica_Spring.Repository.Transacciones.CitaRepository;
import com.example.Sistema_Clinica_Spring.Repository.Transacciones.TransaccionRepository;
import com.example.Sistema_Clinica_Spring.Services.Transacciones.InterfaceService.TransaccionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceTransaccion implements TransaccionService {

    @Autowired
    TransaccionRepository transaccionRepository;

    @Autowired
    ServiceCita serviceCita;

    public List<Transaccion> listarTransacciones(){
        return transaccionRepository.findAll();
    }

    public Transaccion obtenerTransaccion(Long id_transaccion){
        Optional<Transaccion> transaccionEncontrada = transaccionRepository.findById(id_transaccion);
        return transaccionEncontrada.orElse(null);
    }

    public Transaccion crearTransaccion(Transaccion transaccion){
        Transaccion transaccion_guardada = transaccionRepository.save(transaccion);
        return transaccion_guardada;
    }

    public ResponseEntity<String> actualizarTransaccion(Long id_transaccion,Transaccion transaccion){
        Optional<Transaccion> transaccionOptional = transaccionRepository.findById(id_transaccion);

        if (transaccionOptional.isPresent()){
            Transaccion transaccionActualizar = transaccionOptional.get();
            transaccionActualizar.setIdPaciente(transaccion.getIdPaciente());
            transaccionActualizar.setHora(transaccion.getHora());
            transaccionActualizar.setFecha(transaccion.getFecha());
            transaccionActualizar.setTipo(transaccion.getTipo());
            transaccionActualizar.setTotal(transaccion.getTotal());
            transaccionActualizar.setEstado(transaccion.getEstado());
            transaccionActualizar.setCreatedAt(transaccion.getCreatedAt());

            transaccionRepository.save(transaccionActualizar);
            return ResponseEntity.ok("Transaccion actualizada exitosamente");
        }

        return null;
    }

    public ResponseEntity<String> eliminarTransaccion(Long id_transaccion){
        transaccionRepository.deleteById(id_transaccion);
        return ResponseEntity.ok("Transaccion eliminada exitosamente");
    }
}
