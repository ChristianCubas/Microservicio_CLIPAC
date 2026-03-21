package com.example.Sistema_Clinica_Spring.Models.InventarioMedicamentos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "kardex_medicamento")
@Getter
@Setter
public class Kardex_medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_kardex_medicamento")
    private Long id_kardex_medicamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medicamento", nullable = false)
    private Medicamento medicamento;

    @Column(name = "tipo_movimiento", nullable = false)
    private String tipoMovimiento;

    @Column(name = "cantidad",nullable = false)
    private Integer cantidad;

    @Column(name = "motivo",nullable = false)
    private String motivo;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "fecha",nullable = false)
    private LocalDateTime fecha; // fecha de registro de la transacción

    @Column(name = "lote",nullable = true)
    private String lote; // identificador del lote

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fechaExpiracion", nullable = true)
    private LocalDate fechaExpiracion; // fecha de caducidad del lote (solo para entradas)
}