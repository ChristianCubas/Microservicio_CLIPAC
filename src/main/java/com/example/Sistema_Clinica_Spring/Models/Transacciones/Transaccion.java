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
@Getter
@Setter
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long idTransaccion;

    @Column(nullable = false)
    @Getter @Setter
    private Long idPaciente;

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

    @OneToOne(mappedBy = "transaccion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter @Setter
    private Cita cita;

    @OneToOne(mappedBy = "transaccion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter @Setter
    private Examen examen;

    @OneToOne(mappedBy = "transaccion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter @Setter
    private Venta venta;
}
