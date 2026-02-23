package com.example.Sistema_Clinica_Spring.Models.Transacciones;

import com.example.Sistema_Clinica_Spring.Models.Usuarios.Trabajador;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "transaccion")
@Data
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long idTransaccion;

    @Column(nullable = false)
    @Getter @Setter
    private Long idPaciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_trabajador_ventanilla", nullable = false)
    @Getter @Setter
    private Trabajador trabajadorVentanilla;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_trabajador_atencion", nullable = false)
    @Getter @Setter
    private Trabajador trabajadorAtencion;

    @Column(nullable = false)
    @Getter @Setter
    private String tipo;

    @Column(nullable = false)
    @Getter @Setter
    private LocalDate fecha;

    @Column(nullable = false)
    @Getter @Setter
    private LocalTime hora;

    @Column(nullable = false)
    @Getter @Setter
    private BigDecimal total;

    @Column(nullable = false)
    @Getter @Setter
    private Integer estado;

    @Column(nullable = false)
    @Getter @Setter
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "transaccion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter @Setter
    private List<Cita> listadoCitas;

    @OneToMany(mappedBy = "transaccion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter @Setter
    private List<Examen> listadoExamenes;

    @OneToMany(mappedBy = "transaccion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter @Setter
    private List<Venta> listadoVentas;
}
