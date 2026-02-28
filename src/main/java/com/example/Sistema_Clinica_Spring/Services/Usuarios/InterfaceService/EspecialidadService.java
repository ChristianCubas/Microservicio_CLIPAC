package com.example.Sistema_Clinica_Spring.Services.Usuarios.InterfaceService;

import com.example.Sistema_Clinica_Spring.Models.Usuarios.Complemento.Especialidad;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EspecialidadService {
    public List<Especialidad> listarEspecialidades();
    public Especialidad obtenerEspecialidad(long id_especialidad);
    public ResponseEntity<String> crearEspecialidad(Especialidad especialidad);
    public ResponseEntity<String> actualizarEspecialidad(Long id_especialidad, Especialidad cambios);
    public ResponseEntity<String> darBajaEspecialidad(Long id_especialidad);
    public ResponseEntity<String> eliminarEspecialidad(Long id_especialidad);
}
