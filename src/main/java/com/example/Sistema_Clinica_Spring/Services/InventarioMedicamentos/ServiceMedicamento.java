package com.example.Sistema_Clinica_Spring.Services.InventarioMedicamentos;

import com.example.Sistema_Clinica_Spring.Models.InventarioMedicamentos.Medicamento;
import com.example.Sistema_Clinica_Spring.Repository.InventarioMedicamentos.MedicamentoRepository;
import com.example.Sistema_Clinica_Spring.Services.InventarioMedicamentos.InterfaceService.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceMedicamento implements MedicamentoService {
    @Autowired
    MedicamentoRepository medicamentoRepository;

    public List<Medicamento> listarMedicamentos(){
        return  medicamentoRepository.findAll();
    }

    public Medicamento obtenerMedicamento(Long id_medicamento){
        Optional<Medicamento> medicamento = medicamentoRepository.findById(id_medicamento);
        return medicamento.orElse(null);
    }

    public ResponseEntity<String> crearMedicamento(Medicamento medicamento){
        medicamentoRepository.save(medicamento);
        return ResponseEntity.ok("Medicamento creado exitosamente");
    }

    public ResponseEntity<String> actualizarMedicamento(Long id_medicamento, Medicamento medicamento){
        Optional<Medicamento> medicamento_recibido = medicamentoRepository.findById(id_medicamento);

        if (medicamento_recibido.isPresent()){
            Medicamento medicamento_actualizar = medicamento_recibido.get();
            medicamento_actualizar.setTipoMedicamento(medicamento.getTipoMedicamento());
            medicamento_actualizar.setNombre(medicamento.getNombre());
            medicamento_actualizar.setDescripcion(medicamento.getDescripcion());
            medicamento_actualizar.setFechaExpiracion(medicamento.getFechaExpiracion());
            medicamento_actualizar.setPrecio(medicamento.getPrecio());
            medicamento_actualizar.setProveedor(medicamento.getProveedor());
            medicamento_actualizar.setStock(medicamento.getStock());
            medicamento_actualizar.setEstado(medicamento.getEstado());

            medicamentoRepository.save(medicamento_actualizar);
            return ResponseEntity.ok("Medicamento actualizado exitosamente");
        }

        return null;
    }

    public ResponseEntity<String> eliminarMedicamento(Long id_medicamento){
        medicamentoRepository.deleteById(id_medicamento);
        return ResponseEntity.ok("Medicamento eliminado exitosamente");
    }
}
