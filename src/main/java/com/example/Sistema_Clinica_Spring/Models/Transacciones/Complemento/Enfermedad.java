package com.example.Sistema_Clinica_Spring.Models.Transacciones.Complemento;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "enfermedad")
@Getter
@Setter
public class Enfermedad {
    @Id
    @Column(name = "id_enfermedad")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private long idEnfermedad;

    @Column(name = "nombre", nullable = false)
    @Getter @Setter
    private String nombre;

    @Column(name = "codigo", nullable = false)
    @Getter @Setter
    private String codigo;

    @Column(name = "descripcion", nullable = false)
    @Getter @Setter
    private String descripcion;

    @OneToMany(mappedBy = "enfermedad", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Getter @Setter
    private List<Diagnostico> diagnosticos = new ArrayList<>();
}
