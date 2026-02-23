package com.example.Sistema_Clinica_Spring.Services.Transacciones.InterfaceService;

import com.example.Sistema_Clinica_Spring.Models.Transacciones.Transaccion;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TransaccionService {
    public List<Transaccion> listarTransacciones();
    public Transaccion obtenerTransaccion(Long id_transaccion);
    public ResponseEntity<String> crearTransaccion(Transaccion transaccion);
    public ResponseEntity<String> actualizarTransaccion(Long id_transaccion,Transaccion transaccion);
    public ResponseEntity<String> eliminarTransaccion(Long id_transaccion);
}
