package com.example.Sistema_Clinica_Spring.Models.Programacion;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "consultorio")
@Data
public class Consultorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long idConsultorio;

    @Column(nullable = false)
    @Getter @Setter
    private String piso;

    @Column(nullable = false)
    @Getter @Setter
    private Integer estado;

    @ManyToMany(mappedBy = "consultorios", fetch = FetchType.LAZY)
    @Getter @Setter
    private List<Programacion> programaciones;
}
