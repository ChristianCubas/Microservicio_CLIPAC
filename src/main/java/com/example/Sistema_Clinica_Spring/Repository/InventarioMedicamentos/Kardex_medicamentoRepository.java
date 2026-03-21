package com.example.Sistema_Clinica_Spring.Repository.InventarioMedicamentos;

import com.example.Sistema_Clinica_Spring.Models.InventarioMedicamentos.Kardex_medicamento;
import com.example.Sistema_Clinica_Spring.Models.InventarioMedicamentos.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Kardex_medicamentoRepository extends JpaRepository<Kardex_medicamento,Long> {
    List<Kardex_medicamento> findByMedicamentoAndTipoMovimientoAndCantidadGreaterThan(
            Medicamento medicamento,
            String tipoMovimiento,
            Integer cantidad
    );
}
