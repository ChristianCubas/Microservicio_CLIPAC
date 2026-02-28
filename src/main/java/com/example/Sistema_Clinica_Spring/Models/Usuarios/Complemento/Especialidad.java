package com.example.Sistema_Clinica_Spring.Models.Usuarios.Complemento;

import com.example.Sistema_Clinica_Spring.Models.Usuarios.Trabajador;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Especialidad")
@Data
public class Especialidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_especialidad")
    @Getter
    @Setter
    private long id_especialidad;

    @Column(name = "nombre", nullable = false)
    @Getter @Setter
    private String nombre;

    @Column(name = "estado", nullable = false)
    @Getter @Setter
    private int estado;

    @ManyToMany(mappedBy = "especialidades",fetch = FetchType.LAZY)
    @Getter @Setter
    private List<Trabajador> trabajadores;
}
