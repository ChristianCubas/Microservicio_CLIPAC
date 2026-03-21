package com.example.Sistema_Clinica_Spring.Services.InventarioMedicamentos.InterfaceService;

import com.example.Sistema_Clinica_Spring.Models.InventarioMedicamentos.Kardex_medicamento;
import com.example.Sistema_Clinica_Spring.Models.InventarioMedicamentos.Medicamento;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface Kardex_medicamentoService {
    public List<Kardex_medicamento> listarKardexMedicamento();
    public Kardex_medicamento obtenerKardexMedicamento(Long id_kardex);
    public ResponseEntity<String> crearKardexMedicamento(Kardex_medicamento kardex);
    public ResponseEntity<String> actualizarKardexMedicamento(Long id_kardex, Kardex_medicamento kardex);
    public ResponseEntity<String> eliminarKardexMedicamento(Long id_kardex);
}
