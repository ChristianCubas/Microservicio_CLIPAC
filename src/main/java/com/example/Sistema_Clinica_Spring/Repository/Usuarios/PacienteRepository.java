package com.example.Sistema_Clinica_Spring.Repository.Usuarios;

import com.example.Sistema_Clinica_Spring.Models.Usuarios.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente,Long> {
    boolean existsByNroDocumento(String nroDocumento);
}
