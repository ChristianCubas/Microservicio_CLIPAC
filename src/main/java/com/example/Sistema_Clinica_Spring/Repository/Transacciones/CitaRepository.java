package com.example.Sistema_Clinica_Spring.Repository.Transacciones;

import com.example.Sistema_Clinica_Spring.Models.Transacciones.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitaRepository extends JpaRepository<Cita,Long> {
}
