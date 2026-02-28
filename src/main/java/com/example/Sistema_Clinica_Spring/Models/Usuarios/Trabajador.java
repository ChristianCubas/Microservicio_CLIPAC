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

    @Column(name = "nro_documento",nullable = false)
    private String nroDocumento;

    @Column(name = "nombres",nullable = false)
    private String nombres;

    @Column(name = "apellidos",nullable = false)
    private String apellidos;

    @Column(name = "telefono",nullable = false)
    private String telefono;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "direccion",nullable = false)
    private String direccion;

    @Column(name = "colegiatura",nullable = true)
    private String colegiatura;

    @Column(name = "contrasenia",nullable = false)
    private String contrasenia;

    @Column(name = "estado",nullable = false)
    private Integer estado;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

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