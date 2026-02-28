package com.example.Sistema_Clinica_Spring.Models.Usuarios;

import com.example.Sistema_Clinica_Spring.Models.Programacion.Programacion;
import com.example.Sistema_Clinica_Spring.Models.Usuarios.Complemento.Especialidad;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "trabajador")
@Data
@ToString(exclude = {"tipoTrabajador", "especialidades", "programaciones"})
@EqualsAndHashCode(exclude = {"tipoTrabajador", "especialidades", "programaciones"})
public class Trabajador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_trabajador")
    private Long id_trabajador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_trabajador", nullable = false)
    private Tipo_trabajador tipoTrabajador;

    @Column(nullable = false)
    private String nroDocumento;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = true)
    private String colegiatura;

    @Column(nullable = false)
    private String contrasenia;

    @Column(nullable = false)
    private Integer estado;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "trabajador_especialidad",
            joinColumns = @JoinColumn(name = "id_trabajador"),
            inverseJoinColumns = @JoinColumn(name = "id_especialidad")
    )
    private List<Especialidad> especialidades;

    @OneToMany(mappedBy = "trabajador", fetch = FetchType.LAZY)
    private List<Programacion> programaciones;
}