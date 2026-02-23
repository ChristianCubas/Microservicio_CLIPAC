package com.example.Sistema_Clinica_Spring.Models.InventarioMedicamentos;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "kardex_medicamento")
@Data
public class Kardex_medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long idKardexMedicamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medicamento", nullable = false)
    @Getter @Setter
    private Medicamento medicamento;

    @Column(nullable = false)
    @Getter @Setter
    private String tipoMovimiento;

    @Column(nullable = false)
    @Getter @Setter
    private Integer cantidad;

    @Column(nullable = false)
    @Getter @Setter
    private String motivo;

    @Column(nullable = false)
    @Getter @Setter
    private LocalDateTime fecha;
}
