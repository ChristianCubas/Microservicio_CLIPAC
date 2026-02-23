package com.example.Sistema_Clinica_Spring.Repository.Transacciones;

import com.example.Sistema_Clinica_Spring.Models.Transacciones.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta,Long> {
}
