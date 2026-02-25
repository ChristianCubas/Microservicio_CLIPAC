package com.example.Sistema_Clinica_Spring.Models.Usuarios;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "paciente")
@Data
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente")
    @Getter @Setter
    private Long id_paciente;

    @Column(nullable = false)
    @Getter @Setter
    private String tipoDocumento;

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
    private LocalDate fechaNacimiento;

    @Column(nullable = false)
    @Getter @Setter
    private String sexo;

    @Column(nullable = false)
    @Getter @Setter
    private String telefono;

    @Column(nullable = false)
    @Getter @Setter
    private String email;

    @Column(nullable = false)
    @Getter @Setter
    private String direccion;

    @Column(nullable = false)
    @Getter @Setter
    private String grupoSanguineo;

    @Column(nullable = false)
    @Getter @Setter
    private String alergias;

    @Column(nullable = false)
    @Getter @Setter
    private Integer estado;

    @Column(nullable = false)
    @Getter @Setter
    private String contrasenia;

    @Column(nullable = false)
    @Getter @Setter
    private LocalDateTime createdAt;
}
