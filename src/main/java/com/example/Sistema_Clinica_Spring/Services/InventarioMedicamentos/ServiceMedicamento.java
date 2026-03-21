package com.example.Sistema_Clinica_Spring.Services.InventarioMedicamentos;

import com.example.Sistema_Clinica_Spring.Estructuras.TCola;
import com.example.Sistema_Clinica_Spring.Models.InventarioMedicamentos.Kardex_medicamento;
import com.example.Sistema_Clinica_Spring.Models.InventarioMedicamentos.Medicamento;
import com.example.Sistema_Clinica_Spring.Repository.InventarioMedicamentos.Kardex_medicamentoRepository;
import com.example.Sistema_Clinica_Spring.Repository.InventarioMedicamentos.MedicamentoRepository;
import com.example.Sistema_Clinica_Spring.Services.InventarioMedicamentos.InterfaceService.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class ServiceMedicamento implements MedicamentoService {
    @Autowired
    MedicamentoRepository medicamentoRepository;

    @Autowired
    Kardex_medicamentoRepository kardexMedicamentoRepository;

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
            medicamento_actualizar.setPrecio(medicamento.getPrecio());
            medicamento_actualizar.setProveedor(medicamento.getProveedor());
            medicamento_actualizar.setEstado(medicamento.getEstado());
            medicamento_actualizar.setFecha_plazo(medicamento.getFecha_plazo());
            medicamento_actualizar.setStock_unidades(medicamento_actualizar.getStock_unidades());

            medicamentoRepository.save(medicamento_actualizar);
            return ResponseEntity.ok("Medicamento actualizado exitosamente");
        }

        return null;
    }

    public ResponseEntity<String> darBajaMedicamento(Long id_medicamento){
        Optional<Medicamento> medicamento_recibido = medicamentoRepository.findById(id_medicamento);

        if (medicamento_recibido.isPresent()){
            Medicamento medicamento_actualizar = medicamento_recibido.get();

            medicamento_actualizar.setEstado(0);

            medicamentoRepository.save(medicamento_actualizar);
            return ResponseEntity.ok("Medicamento dado de baja exitosamente");
        }

        return null;
    }

    public ResponseEntity<String> eliminarMedicamento(Long id_medicamento){
        medicamentoRepository.deleteById(id_medicamento);
        return ResponseEntity.ok("Medicamento eliminado exitosamente");
    }

    public List<Medicamento> listarMedicamentosPorProveedor(Long idProveedor) {
        return medicamentoRepository.findByProveedorId(idProveedor);
    }

    public TCola FEFOMedicamento(){
        List<Kardex_medicamento> lotes_totales = kardexMedicamentoRepository.findAll();
        List<Kardex_medicamento> lotes_unicos = new ArrayList<>();
        Map<String, Integer> stockPorLote = new HashMap<>();
        Map<String, Kardex_medicamento> referenciaLote = new HashMap<>();

        TCola TMedicamentos = new TCola();

        LocalDate hoy = LocalDate.now();

        // Calcular stock por lote
        for(Kardex_medicamento kardex : lotes_totales){

            String clave = kardex.getMedicamento().getId_medicamento()
                    + "-" + kardex.getLote();

            referenciaLote.putIfAbsent(clave, kardex);

            int cantidad = kardex.getCantidad();

            if (kardex.getTipoMovimiento().equalsIgnoreCase("entrada")){
                stockPorLote.put(clave, stockPorLote.getOrDefault(clave, 0) + cantidad);
            } else {
                stockPorLote.put(clave, stockPorLote.getOrDefault(clave, 0) - cantidad);
            }
        }

        // Crear lotes únicos con stock real
        for(String clave : stockPorLote.keySet()){

            int stockActual = stockPorLote.get(clave);

            if (stockActual > 0){

                Kardex_medicamento kardexBase = referenciaLote.get(clave);

                Kardex_medicamento kardexFinal = new Kardex_medicamento();
                kardexFinal.setMedicamento(kardexBase.getMedicamento());
                kardexFinal.setLote(kardexBase.getLote());
                kardexFinal.setFechaExpiracion(kardexBase.getFechaExpiracion());
                kardexFinal.setCantidad(stockActual);

                lotes_unicos.add(kardexFinal);
            }
        }

        // FEFO
        for(Kardex_medicamento kardex : lotes_unicos){
            if (kardex.getFechaExpiracion() != null){

                int tiempoVida = (int) ChronoUnit.DAYS.between(
                        hoy,
                        kardex.getFechaExpiracion()
                );

                TMedicamentos.insertar(kardex, -tiempoVida);
            }
        }

        return TMedicamentos;
    }

    //  Filtrar por proveedor
    public TCola FiltroPorProveedor(Long idProveedor){
        List<Kardex_medicamento> lotes_totales = kardexMedicamentoRepository.findAll();
        List<Kardex_medicamento> lotes_unicos = new ArrayList<>();
        Map<String, Integer> stockPorLote = new HashMap<>();
        Map<String, Kardex_medicamento> referenciaLote = new HashMap<>();

        TCola TMedicamentos = new TCola();
        LocalDate hoy = LocalDate.now();

        // Filtrar y calcular stock por lote
        for(Kardex_medicamento kardex : lotes_totales){
            if (!kardex.getMedicamento().getProveedor().getId_proveedor().equals(idProveedor)) continue;

            String clave = kardex.getMedicamento().getId_medicamento() + "-" + kardex.getLote();
            referenciaLote.putIfAbsent(clave, kardex);

            int cantidad = kardex.getCantidad();
            if (kardex.getTipoMovimiento().equalsIgnoreCase("entrada")){
                stockPorLote.put(clave, stockPorLote.getOrDefault(clave, 0) + cantidad);
            } else {
                stockPorLote.put(clave, stockPorLote.getOrDefault(clave, 0) - cantidad);
            }
        }

        // Crear lotes únicos con stock real
        for(String clave : stockPorLote.keySet()){
            int stockActual = stockPorLote.get(clave);
            if (stockActual > 0){
                Kardex_medicamento kardexBase = referenciaLote.get(clave);
                Kardex_medicamento kardexFinal = new Kardex_medicamento();
                kardexFinal.setMedicamento(kardexBase.getMedicamento());
                kardexFinal.setLote(kardexBase.getLote());
                kardexFinal.setFechaExpiracion(kardexBase.getFechaExpiracion());
                kardexFinal.setCantidad(stockActual);
                lotes_unicos.add(kardexFinal);
            }
        }

        // Insertar en la cola FEFO
        for(Kardex_medicamento kardex : lotes_unicos){
            if (kardex.getFechaExpiracion() != null){
                int tiempoVida = (int) ChronoUnit.DAYS.between(hoy, kardex.getFechaExpiracion());
                TMedicamentos.insertar(kardex, -tiempoVida);
            }
        }

        return TMedicamentos;
    }

    // Filtrar por medicamento
    public TCola FiltroPorMedicamento(Long idMedicamento){
        List<Kardex_medicamento> lotes_totales = kardexMedicamentoRepository.findAll();
        List<Kardex_medicamento> lotes_unicos = new ArrayList<>();
        Map<String, Integer> stockPorLote = new HashMap<>();
        Map<String, Kardex_medicamento> referenciaLote = new HashMap<>();

        TCola TMedicamentos = new TCola();
        LocalDate hoy = LocalDate.now();

        // Filtrar y calcular stock por lote
        for(Kardex_medicamento kardex : lotes_totales){
            if (!kardex.getMedicamento().getId_medicamento().equals(idMedicamento)) continue;

            String clave = kardex.getMedicamento().getId_medicamento() + "-" + kardex.getLote();
            referenciaLote.putIfAbsent(clave, kardex);

            int cantidad = kardex.getCantidad();
            if (kardex.getTipoMovimiento().equalsIgnoreCase("entrada")){
                stockPorLote.put(clave, stockPorLote.getOrDefault(clave, 0) + cantidad);
            } else {
                stockPorLote.put(clave, stockPorLote.getOrDefault(clave, 0) - cantidad);
            }
        }

        // Crear lotes únicos con stock real
        for(String clave : stockPorLote.keySet()){
            int stockActual = stockPorLote.get(clave);
            if (stockActual > 0){
                Kardex_medicamento kardexBase = referenciaLote.get(clave);
                Kardex_medicamento kardexFinal = new Kardex_medicamento();
                kardexFinal.setMedicamento(kardexBase.getMedicamento());
                kardexFinal.setLote(kardexBase.getLote());
                kardexFinal.setFechaExpiracion(kardexBase.getFechaExpiracion());
                kardexFinal.setCantidad(stockActual);
                lotes_unicos.add(kardexFinal);
            }
        }

        // Insertar en la cola FEFO
        for(Kardex_medicamento kardex : lotes_unicos){
            if (kardex.getFechaExpiracion() != null){
                int tiempoVida = (int) ChronoUnit.DAYS.between(hoy, kardex.getFechaExpiracion());
                TMedicamentos.insertar(kardex, -tiempoVida);
            }
        }

        return TMedicamentos;
    }

    // Filtrar por proveedor y medicamento
    public TCola FiltroPorProveedorYMedicamento(Long idProveedor, Long idMedicamento){
        List<Kardex_medicamento> lotes_totales = kardexMedicamentoRepository.findAll();
        List<Kardex_medicamento> lotes_unicos = new ArrayList<>();
        Map<String, Integer> stockPorLote = new HashMap<>();
        Map<String, Kardex_medicamento> referenciaLote = new HashMap<>();

        TCola TMedicamentos = new TCola();
        LocalDate hoy = LocalDate.now();

        // Filtrar y calcular stock por lote
        for(Kardex_medicamento kardex : lotes_totales){
            // Filtrar por proveedor y medicamento
            if (!kardex.getMedicamento().getProveedor().getId_proveedor().equals(idProveedor)) continue;
            if (!kardex.getMedicamento().getId_medicamento().equals(idMedicamento)) continue;

            String clave = kardex.getMedicamento().getId_medicamento() + "-" + kardex.getLote();
            referenciaLote.putIfAbsent(clave, kardex);

            int cantidad = kardex.getCantidad();
            if (kardex.getTipoMovimiento().equalsIgnoreCase("entrada")){
                stockPorLote.put(clave, stockPorLote.getOrDefault(clave, 0) + cantidad);
            } else {
                stockPorLote.put(clave, stockPorLote.getOrDefault(clave, 0) - cantidad);
            }
        }

        // Crear lotes únicos con stock real
        for(String clave : stockPorLote.keySet()){
            int stockActual = stockPorLote.get(clave);
            if (stockActual > 0){
                Kardex_medicamento kardexBase = referenciaLote.get(clave);
                Kardex_medicamento kardexFinal = new Kardex_medicamento();
                kardexFinal.setMedicamento(kardexBase.getMedicamento());
                kardexFinal.setLote(kardexBase.getLote());
                kardexFinal.setFechaExpiracion(kardexBase.getFechaExpiracion());
                kardexFinal.setCantidad(stockActual);
                lotes_unicos.add(kardexFinal);
            }
        }

        // Insertar en la cola FEFO
        for(Kardex_medicamento kardex : lotes_unicos){
            if (kardex.getFechaExpiracion() != null){
                int tiempoVida = (int) ChronoUnit.DAYS.between(hoy, kardex.getFechaExpiracion());
                TMedicamentos.insertar(kardex, -tiempoVida);
            }
        }

        return TMedicamentos;
    }
}
