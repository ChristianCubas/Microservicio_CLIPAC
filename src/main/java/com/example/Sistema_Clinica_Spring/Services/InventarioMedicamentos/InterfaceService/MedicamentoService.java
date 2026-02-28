package com.example.Sistema_Clinica_Spring.Services.InventarioMedicamentos.InterfaceService;

import com.example.Sistema_Clinica_Spring.Models.InventarioMedicamentos.Medicamento;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MedicamentoService {
    public List<Medicamento> listarMedicamentos();
    public Medicamento obtenerMedicamento(Long id_medicamento);
    public ResponseEntity<String> crearMedicamento(Medicamento medicamento);
    public ResponseEntity<String> actualizarMedicamento(Long id_medicamento, Medicamento medicamento);
    public ResponseEntity<String> darBajaMedicamento(Long id_medicamento);
    public ResponseEntity<String> eliminarMedicamento(Long id_medicamento);
}
