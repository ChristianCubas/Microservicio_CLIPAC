package com.example.Sistema_Clinica_Spring.Models.Programacion;

import com.example.Sistema_Clinica_Spring.Models.Transacciones.Cita;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Horario")
@Data
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_horario")
    @Getter @Setter
    private long id_horario;

    @OneToMany(mappedBy = "horario")
    private List<Programacion> programaciones;

    @Column(name = "hora_inicio", nullable = false)
    @Getter @Setter
    private String hora_inicio;

    @Column(name = "hora_fin", nullable = false)
    @Getter @Setter
    private String hora_fin;

    @Column(name = "estado", nullable = false)
    @Getter @Setter
    private int estado;

    @OneToMany(mappedBy = "horario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter @Setter
    private List<Cita> listadoCitas;
}
