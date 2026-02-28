package com.example.Sistema_Clinica_Spring.Models.Programacion;

import com.example.Sistema_Clinica_Spring.Models.Usuarios.Trabajador;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "programacion")
@Data
public class Programacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProgramacion;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private Integer dia;

    @Column(nullable = false)
    private Integer estado;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_trabajador", nullable = false)
    private Trabajador trabajador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_consultorio", nullable = false)
    private Consultorio consultorio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_horario", nullable = false)
    private Horario horario;
}