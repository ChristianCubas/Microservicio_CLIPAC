package com.example.Sistema_Clinica_Spring.Repository.Transacciones;

import com.example.Sistema_Clinica_Spring.Models.Transacciones.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaccionRepository extends JpaRepository<Transaccion,Long> {
}
