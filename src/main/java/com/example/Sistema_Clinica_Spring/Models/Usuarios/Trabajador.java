package com.example.Sistema_Clinica_Spring.Models.Usuarios;

import com.example.Sistema_Clinica_Spring.Models.Usuarios.Complemento.Especialidad;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "trabajador")
@Data
public class Trabajador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_trabajador")
    @Getter
    @Setter
    private Long id_trabajador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_trabajador", nullable = false)
    @Getter @Setter
    private Tipo_trabajador tipoTrabajador;

    @Column(nullable = false)
    @Getter @Setter
    private String nroDocumento;

    @Column(nullable = false)
    @Getter @Setter
    private String nombres;

    @Column(nullable = false)
    @Getter @Setter
    private String apellidos;

    @Column(nullable = false)
    @Getter @Setter
    private String telefono;

    @Column(nullable = false)
    @Getter @Setter
    private String email;

    @Column(nullable = false)
    @Getter @Setter
    private String direccion;

    @Column(nullable = true)
    @Getter @Setter
    private String colegiatura;

    @Column(nullable = false)
    @Getter @Setter
    private String contrasenia;

    @Column(nullable = false)
    @Getter @Setter
    private Integer estado;

    @Column(nullable = false)
    @Getter @Setter
    private LocalDateTime createdAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "trabajador_especialidad",
            joinColumns = @JoinColumn(name = "id_trabajador"),
            inverseJoinColumns = @JoinColumn(name = "id_especialidad")
    )
    @Getter @Setter
    private List<Especialidad> especialidades;
}
