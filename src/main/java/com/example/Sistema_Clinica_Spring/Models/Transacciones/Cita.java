package com.example.Sistema_Clinica_Spring.Models.Transacciones;

import com.example.Sistema_Clinica_Spring.Models.InventarioMedicamentos.Medicamento;
import com.example.Sistema_Clinica_Spring.Models.Programacion.Horario;
import com.example.Sistema_Clinica_Spring.Models.Programacion.Programacion;
import com.example.Sistema_Clinica_Spring.Models.Transacciones.Complemento.Diagnostico;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "cita")
@Getter
@Setter
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    @Getter
    @Setter
    private Long id_cita;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_transaccion", nullable = false)
    @Getter @Setter
    private Transaccion transaccion;

    @Column(nullable = false)
    @Getter @Setter
    private Integer modalidad;

    @Column(nullable = false)
    @Getter @Setter
    private Integer estado;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_programacion", nullable = false)
    @Getter @Setter
    private Programacion programacion;

    @OneToOne(mappedBy = "cita", fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @Getter @Setter
    private Diagnostico diagnostico;
}
