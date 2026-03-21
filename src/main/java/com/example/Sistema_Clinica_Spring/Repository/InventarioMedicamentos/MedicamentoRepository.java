package com.example.Sistema_Clinica_Spring.Repository.InventarioMedicamentos;

import com.example.Sistema_Clinica_Spring.Models.InventarioMedicamentos.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicamentoRepository extends JpaRepository<Medicamento,Long> {
    @Query("SELECT m FROM Medicamento m WHERE m.proveedor.id_proveedor = :idProveedor")
    List<Medicamento> findByProveedorId(@Param("idProveedor") Long idProveedor);
}
