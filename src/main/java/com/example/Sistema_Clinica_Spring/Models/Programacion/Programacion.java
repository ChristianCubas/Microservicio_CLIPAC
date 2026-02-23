package com.example.Sistema_Clinica_Spring.Models.Programacion;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "programacion")
@Data
public class Programacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long idProgramacion;

    @Column(nullable = false)
    @Getter @Setter
    private LocalDate fecha;

    @Column(nullable = false)
    @Getter @Setter
    private Integer dia;

    @Column(nullable = false)
    @Getter @Setter
    private Integer estado;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "programacion_horario",
            joinColumns = @JoinColumn(name = "id_programacion"),
            inverseJoinColumns = @JoinColumn(name = "id_horario")
    )
    @Getter @Setter
    private List<Horario> horarios;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "consultorio_programacion",
            joinColumns = @JoinColumn(name = "id_programacion"),
            inverseJoinColumns = @JoinColumn(name = "id_consultorio")
    )
    @Getter @Setter
    private List<Consultorio> consultorios;
}
