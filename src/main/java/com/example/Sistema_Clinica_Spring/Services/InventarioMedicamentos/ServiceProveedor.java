package com.example.Sistema_Clinica_Spring.Services.InventarioMedicamentos;

import com.example.Sistema_Clinica_Spring.Models.InventarioMedicamentos.Proveedor;
import com.example.Sistema_Clinica_Spring.Repository.InventarioMedicamentos.ProveedorRepository;
import com.example.Sistema_Clinica_Spring.Services.InventarioMedicamentos.InterfaceService.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public class ServiceProveedor implements ProveedorService {
    @Autowired
    ProveedorRepository proveedorRepository;

    public List<Proveedor> listarProveedores(){
        return proveedorRepository.findAll();
    }

    public Proveedor obtenerProveedor(Long id_proveedor){
        Optional<Proveedor> proveedor = proveedorRepository.findById(id_proveedor);
        return proveedor.orElse(null);
    }

    public ResponseEntity<String> crearProveedor(Proveedor proveedor){
        proveedorRepository.save(proveedor);
        return ResponseEntity.ok("Proveedor creado exitosamente");
    }

    public ResponseEntity<String> actualizarProveedor(Long id_proveedor,Proveedor proveedor){
        Optional<Proveedor> proveedor_encontrado = proveedorRepository.findById(id_proveedor);
        if (proveedor_encontrado.isPresent()){
            Proveedor proveedorActualizado = proveedor_encontrado.get();

            proveedorActualizado.setRazonSocial(proveedor.getRazonSocial());
            proveedorActualizado.setNroRuc(proveedor.getNroRuc());
            proveedorActualizado.setTelefono(proveedor.getTelefono());
            proveedorActualizado.setCorreo(proveedor.getCorreo());
            proveedorActualizado.setDireccion(proveedor.getDireccion());
            proveedorActualizado.setEstado(proveedor.getEstado());
            proveedorActualizado.setCuentaBancaria(proveedor.getCuentaBancaria());

            proveedorRepository.save(proveedorActualizado);

            return ResponseEntity.ok("Proveedor actualizado exitosamente");
        }

        return null;
    }

    public ResponseEntity<String> eliminarProveedor(Long id_proveedor){
        proveedorRepository.deleteById(id_proveedor);
        return ResponseEntity.ok("Proveedor eliminado exitosamente");
    }
}
