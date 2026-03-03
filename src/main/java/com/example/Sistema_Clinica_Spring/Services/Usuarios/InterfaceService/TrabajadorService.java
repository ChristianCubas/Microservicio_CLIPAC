package com.example.Sistema_Clinica_Spring.Services.Usuarios.InterfaceService;

import com.example.Sistema_Clinica_Spring.Models.Usuarios.Trabajador;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TrabajadorService {
    public List<Trabajador> listarTrabajadores();
    public List<Trabajador> listarMedicos();
    public Trabajador obtenerTrabajador(Long id_trabajador);
    public ResponseEntity<String> crearTrabajador(Trabajador trabajador);
    public ResponseEntity<String> actualizarTrabajador(Long id_trabajador, Trabajador trabajador);
    public ResponseEntity<String> eliminarTrabajador(Long id_trabajador);
    public Trabajador logearTrabajador(String email, String contrasenia);
    public ResponseEntity<String> darBajaTrabajador(Long id);
}
