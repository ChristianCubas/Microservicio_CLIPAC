package com.example.Sistema_Clinica_Spring.Services.Usuarios;

import com.example.Sistema_Clinica_Spring.Models.Usuarios.Paciente;
import com.example.Sistema_Clinica_Spring.Repository.Usuarios.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;
import java.util.Optional;

@Service
public class ServicePaciente {
    @Autowired
    PacienteRepository pacienteRepository;

    public List<Paciente> listarPacientes(){
        return pacienteRepository.findAll();
    }

    public Paciente obtenerPaciente(Long id_paciente){
        Optional<Paciente> paciente = pacienteRepository.findById(id_paciente);
        return paciente.orElse(null);
    }

    public ResponseEntity<String> crearPaciente(Paciente paciente){
        pacienteRepository.save(paciente);
        return ResponseEntity.ok("Paciente creado exitosamente");
    }

    public ResponseEntity<String> actualizarPaciente(Long id_paciente, Paciente paciente){
        Optional<Paciente> paciente_recibido = pacienteRepository.findById(id_paciente);

        if (paciente_recibido.isPresent()){
            Paciente paciente_actualizar = paciente_recibido.get();
            paciente_actualizar.setNombres(paciente.getNombres());
            paciente_actualizar.setApellidos(paciente.getApellidos());
            paciente_actualizar.setEmail(paciente.getEmail());
            paciente_actualizar.setDireccion(paciente.getDireccion());
            paciente_actualizar.setFechaNacimiento(paciente.getFechaNacimiento());
            paciente_actualizar.setAlergias(paciente.getAlergias());
            paciente_actualizar.setGrupoSanguineo(paciente.getGrupoSanguineo());
            paciente_actualizar.setNroDocumento(paciente.getNroDocumento());
            paciente_actualizar.setTelefono(paciente.getTelefono());
            paciente_actualizar.setSexo(paciente.getSexo());
            paciente_actualizar.setTipoDocumento(paciente.getTipoDocumento());
            paciente_actualizar.setContrasenia(paciente.getContrasenia());
            paciente_actualizar.setEstado(paciente.getEstado());

            pacienteRepository.save(paciente_actualizar);
            ResponseEntity.ok("Paciente actualizado exitosamente");
        }
        return null;
    }

    public ResponseEntity<String> eliminarPaciente(Long id_paciente){
        pacienteRepository.deleteById(id_paciente);
        return ResponseEntity.ok("Paciente eliminado exitosamente");
    }
}
