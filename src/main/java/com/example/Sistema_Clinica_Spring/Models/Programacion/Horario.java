package com.example.Sistema_Clinica_Spring.Models.Programacion;

import com.example.Sistema_Clinica_Spring.Models.InventarioMedicamentos.Medicamento;
import com.example.Sistema_Clinica_Spring.Models.Transacciones.Cita;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Table(name = "Horario")
@Data
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private long id_horario;

    @ManyToMany(mappedBy = "horarios", fetch = FetchType.LAZY)
    @Getter @Setter
    private ArrayList<Programacion> programaciones;

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
    private ArrayList<Cita> listadoCitas;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "prescripcion_medica",
            joinColumns = @JoinColumn(name = "id_cita"),
            inverseJoinColumns = @JoinColumn(name = "id_medicamento")
    )
    @Getter @Setter
    private ArrayList<Medicamento> listadoMedicamentos;
}
