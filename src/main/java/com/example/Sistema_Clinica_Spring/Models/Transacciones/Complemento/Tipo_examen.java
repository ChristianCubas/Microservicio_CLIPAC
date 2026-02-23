package com.example.Sistema_Clinica_Spring.Models.Transacciones.Complemento;

import com.example.Sistema_Clinica_Spring.Models.Transacciones.Examen;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tipo_examen")
public class Tipo_examen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id_tipo_examen;

    @Column(name = "nombre", nullable = false)
    @Getter @Setter
    private String nombre;

    @Column(name = "descripcion", nullable = false)
    @Getter @Setter
    private String descripcion;

    @Column(name = "estado", nullable = false)
    @Getter @Setter
    private int estado;

    @OneToMany(mappedBy = "tipoExamen", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter @Setter
    private List<Examen> listadoExamenes = new ArrayList<>();
}
