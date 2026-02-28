package com.example.Sistema_Clinica_Spring.Services.InventarioMedicamentos.InterfaceService;

import com.example.Sistema_Clinica_Spring.Models.InventarioMedicamentos.Tipo_medicamento;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface Tipo_medicamentoService {
    public List<Tipo_medicamento> listadoTipoMedicamento();
    public Tipo_medicamento obtenerTipoMedicamento(Long id_tipo_medicamento);
    public ResponseEntity<String> crearTipoMedicamento(Tipo_medicamento tipo_medicamento);
    public ResponseEntity<String> actualizarMedicamento(Long id_tipo_medicamento, Tipo_medicamento tipo_medicamento);
    public ResponseEntity<String> darBajaTipoMedicamento(Long id_tipo_medicamento);
    public ResponseEntity<String> eliminarMedicamento(Long id_tipo_medicamento);
}
