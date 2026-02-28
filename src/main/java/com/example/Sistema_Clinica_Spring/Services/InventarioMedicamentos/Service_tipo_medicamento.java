package com.example.Sistema_Clinica_Spring.Services.InventarioMedicamentos;

import com.example.Sistema_Clinica_Spring.Models.InventarioMedicamentos.Tipo_medicamento;
import com.example.Sistema_Clinica_Spring.Repository.InventarioMedicamentos.Tipo_medicamentoRepository;
import com.example.Sistema_Clinica_Spring.Services.InventarioMedicamentos.InterfaceService.Tipo_medicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Service_tipo_medicamento implements Tipo_medicamentoService {
    @Autowired
    Tipo_medicamentoRepository tipoMedicamentoRepository;

    public List<Tipo_medicamento> listadoTipoMedicamento(){
        return tipoMedicamentoRepository.findAll();
    }

    public Tipo_medicamento obtenerTipoMedicamento(Long id_tipo_medicamento){
        Optional<Tipo_medicamento> tipo_medicamento = tipoMedicamentoRepository.findById(id_tipo_medicamento);
        return tipo_medicamento.orElse(null);
    }

    public ResponseEntity<String> crearTipoMedicamento(Tipo_medicamento tipo_medicamento){
        tipoMedicamentoRepository.save(tipo_medicamento);
        return ResponseEntity.ok("Tipo medicamento creado exitosamente");
    }

    public ResponseEntity<String> actualizarMedicamento(Long id_tipo_medicamento, Tipo_medicamento tipo_medicamento){
        Optional<Tipo_medicamento> tipo_medicamento_recibido = tipoMedicamentoRepository.findById(id_tipo_medicamento);

        if (tipo_medicamento_recibido.isPresent()){
            Tipo_medicamento tipmed = tipo_medicamento_recibido.get();
            tipmed.setNombre(tipo_medicamento.getNombre());
            tipmed.setEstado(tipo_medicamento.getEstado());

            tipoMedicamentoRepository.save(tipmed);

            return ResponseEntity.ok("Tipo medicamento actualizado exitosamente");
        }

        return null;
    }

    public ResponseEntity<String> darBajaTipoMedicamento(Long id_tipo_medicamento){
        Optional<Tipo_medicamento> tipo_medicamento_recibido = tipoMedicamentoRepository.findById(id_tipo_medicamento);

        if (tipo_medicamento_recibido.isPresent()){
            Tipo_medicamento tipmed = tipo_medicamento_recibido.get();
            tipmed.setEstado(0);
            tipoMedicamentoRepository.save(tipmed);

            return ResponseEntity.ok("Tipo medicamento dado de baja exitosamente");
        }

        return null;
    }

    public ResponseEntity<String> eliminarMedicamento(Long id_tipo_medicamento){
        tipoMedicamentoRepository.deleteById(id_tipo_medicamento);
        return ResponseEntity.ok("Tipo medicamento eliminado exitosamente");
    }
}
