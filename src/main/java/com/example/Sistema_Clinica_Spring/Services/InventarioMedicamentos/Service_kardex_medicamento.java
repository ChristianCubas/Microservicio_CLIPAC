package com.example.Sistema_Clinica_Spring.Services.InventarioMedicamentos;

import com.example.Sistema_Clinica_Spring.Models.InventarioMedicamentos.Kardex_medicamento;
import com.example.Sistema_Clinica_Spring.Repository.InventarioMedicamentos.Kardex_medicamentoRepository;
import com.example.Sistema_Clinica_Spring.Services.InventarioMedicamentos.InterfaceService.Kardex_medicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Service_kardex_medicamento implements Kardex_medicamentoService {
    @Autowired
    Kardex_medicamentoRepository kardexMedicamentoRepository;

    public List<Kardex_medicamento> listarKardexMedicamento(){
        return kardexMedicamentoRepository.findAll();
    }

    public Kardex_medicamento obtenerKardexMedicamento(Long id_kardex){
        Optional<Kardex_medicamento> kardex = kardexMedicamentoRepository.findById(id_kardex);
        return kardex.orElse(null);
    }

    public ResponseEntity<String> crearKardexMedicamento(Kardex_medicamento kardex){
        kardexMedicamentoRepository.save(kardex);
        return ResponseEntity.ok("movimiento kardex registrado correctamente");
    }

    public ResponseEntity<String> actualizarKardexMedicamento(Long id_kardex, Kardex_medicamento kardex){
        Optional<Kardex_medicamento> kardex_medicamento = kardexMedicamentoRepository.findById(id_kardex);

        if (kardex_medicamento.isPresent()){
            Kardex_medicamento kardex_med = kardex_medicamento.get();
            kardex_med.setMedicamento(kardex.getMedicamento());
            kardex_med.setCantidad(kardex.getCantidad());
            kardex_med.setFecha(kardex.getFecha());
            kardex_med.setMotivo(kardex.getMotivo());
            kardex_med.setTipoMovimiento(kardex.getTipoMovimiento());

            kardexMedicamentoRepository.save(kardex_med);
            return ResponseEntity.ok("Kardex actualizado correctamente");
        }

        return null;
    }

    public ResponseEntity<String> eliminarKardexMedicamento(Long id_kardex){
        kardexMedicamentoRepository.deleteById(id_kardex);
        return ResponseEntity.ok("Movimiento kardex eliminado exitosamente");
    }
}
