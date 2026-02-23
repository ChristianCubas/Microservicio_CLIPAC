package com.example.Sistema_Clinica_Spring.Services.Transacciones.InterfaceService;

import com.example.Sistema_Clinica_Spring.Models.Transacciones.Venta;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VentaService {
    public List<Venta> listarVentas();
    public Venta obtenerVenta(Long id_venta);
    public ResponseEntity<String> crearVenta(Venta venta);
    public ResponseEntity<String> actualizarVenta(Long id_venta,Venta venta);
    public ResponseEntity<String> eliminarVenta(Long id_venta);
}
