package com.example.Sistema_Clinica_Spring.Services.Transacciones;

import com.example.Sistema_Clinica_Spring.Models.Transacciones.Venta;
import com.example.Sistema_Clinica_Spring.Repository.Transacciones.VentaRepository;
import com.example.Sistema_Clinica_Spring.Services.Transacciones.InterfaceService.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceVenta implements VentaService {
    @Autowired
    VentaRepository ventaRepository;

    public List<Venta> listarVentas(){
        return ventaRepository.findAll();
    }

    public Venta obtenerVenta(Long id_venta){
        Optional<Venta> ventaEncontrada = ventaRepository.findById(id_venta);
        return ventaEncontrada.orElse(null);
    }

    public ResponseEntity<String> crearVenta(Venta venta){
        ventaRepository.save(venta);
        return ResponseEntity.ok("Venta registrada exitosamente");
    }

    public ResponseEntity<String> actualizarVenta(Long id_venta,Venta venta){
        Optional<Venta> ventaEncontrada = ventaRepository.findById(id_venta);

        if (ventaEncontrada.isPresent()){
            Venta ventaActualizar = ventaEncontrada.get();
            ventaActualizar.setIgv(venta.getIgv());
            ventaActualizar.setTransaccion(venta.getTransaccion());
            ventaActualizar.setMontoTotal(venta.getMontoTotal());

            ventaRepository.save(ventaActualizar);
            return ResponseEntity.ok("Venta actualizada exitosamente");
        }

        return null;
    }

    public ResponseEntity<String> eliminarVenta(Long id_venta){
        ventaRepository.deleteById(id_venta);
        return ResponseEntity.ok("Venta eliminada exitosamente");
    }
}
