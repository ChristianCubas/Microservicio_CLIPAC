package com.example.Sistema_Clinica_Spring.Services.InventarioMedicamentos.InterfaceService;

import com.example.Sistema_Clinica_Spring.Models.InventarioMedicamentos.Proveedor;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProveedorService {
    public List<Proveedor> listarProveedores();
    public Proveedor obtenerProveedor(Long id_proveedor);
    public ResponseEntity<String> crearProveedor(Proveedor proveedor);
    public ResponseEntity<String> actualizarProveedor(Long id_proveedor,Proveedor proveedor);
    public ResponseEntity<String> darBajaProveedor(Long id_proveedor);
    public ResponseEntity<String> eliminarProveedor(Long id_proveedor);
}
