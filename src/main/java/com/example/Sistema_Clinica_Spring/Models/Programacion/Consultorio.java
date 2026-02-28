package com.example.Sistema_Clinica_Spring.Models.Programacion;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "consultorio")
@Data
public class Consultorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consultorio")
    private Long id_consultorio;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String piso;

    @Column(nullable = false)
    private Integer estado;

    @OneToMany(mappedBy = "consultorio", fetch = FetchType.LAZY)
    private List<Programacion> programaciones;
}