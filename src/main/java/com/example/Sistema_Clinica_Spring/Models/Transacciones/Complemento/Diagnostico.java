package com.example.Sistema_Clinica_Spring.Models.Transacciones.Complemento;

import com.example.Sistema_Clinica_Spring.Models.InventarioMedicamentos.Medicamento;
import com.example.Sistema_Clinica_Spring.Models.Transacciones.Cita;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "diagnostico")
@Getter
@Setter
public class Diagnostico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_diagnostico")
    private Long idDiagnostico;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "observaciones", length = 2000)
    private String observaciones;

    @Column(name = "fecha_diagnostico")
    private LocalDateTime fechaDiagnostico;

    @Column(name = "estado")
    private Integer estado;

    // Relación con Cita (Un diagnóstico por cita)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cita")
    private Cita cita;

    // Relación con Enfermedad (Diagnóstico específico)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_enfermedad")
    private Enfermedad enfermedad;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "prescripcion_medica",
            joinColumns = @JoinColumn(name = "id_diagnostico"),
            inverseJoinColumns = @JoinColumn(name = "id_medicamento")
    )
    @Getter @Setter
    private List<Medicamento> listadoMedicamentos;

    public Diagnostico() {}

    public Diagnostico(String descripcion, Enfermedad enfermedad, Cita cita) {
        this.descripcion = descripcion;
        this.enfermedad = enfermedad;
        this.cita = cita;
        this.fechaDiagnostico = LocalDateTime.now();
        this.estado = 1;
    }
}
