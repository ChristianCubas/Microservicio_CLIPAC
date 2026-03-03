package com.example.Sistema_Clinica_Spring.Models.Programacion;

import com.example.Sistema_Clinica_Spring.Models.Transacciones.Cita;
import com.example.Sistema_Clinica_Spring.Models.Usuarios.Trabajador;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "programacion")
@Getter
@Setter
public class Programacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_programacion")
    @Getter @Setter
    private Long id_programacion;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Getter @Setter
    private LocalDate fecha;

    @Column(nullable = false)
    @Getter @Setter
    private Integer dia;

    @Column(nullable = false)
    @Getter @Setter
    private Integer estado;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_trabajador", nullable = false)
    @Getter @Setter
    private Trabajador trabajador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_consultorio", nullable = false)
    @Getter @Setter
    private Consultorio consultorio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_horario", nullable = false)
    @Getter @Setter
    private Horario horario;

    @OneToOne(mappedBy = "programacion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter @Setter
    private Cita cita;
}