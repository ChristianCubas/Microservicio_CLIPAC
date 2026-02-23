package com.example.Sistema_Clinica_Spring.Services.Usuarios.InterfaceService;

import com.example.Sistema_Clinica_Spring.Models.Usuarios.Tipo_trabajador;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface Tipo_trabajadorService {
    public List<Tipo_trabajador> listarTipoTrabajadores();
    public Tipo_trabajador obtenerTipoTrabajador(Long id_tipo_trabajador);
    public ResponseEntity<String> crearTipoTrabajador(Tipo_trabajador tipo_trabajador);
    public ResponseEntity<String> actualizarTipoTrabajador(Long id_tipo_trabajador, Tipo_trabajador tipo_trabajador);
    public ResponseEntity<String> eliminarTipoTrabajador(Long id_tipo_trabajador);
}
