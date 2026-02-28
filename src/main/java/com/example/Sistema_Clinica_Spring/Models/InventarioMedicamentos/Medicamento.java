package com.example.Sistema_Clinica_Spring.Models.InventarioMedicamentos;

import com.example.Sistema_Clinica_Spring.Models.Transacciones.Cita;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "medicamento")
@Data
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long idMedicamento;

    @Column(nullable = false)
    @Getter @Setter
    private String nombre;

    @Column(nullable = false)
    @Getter @Setter
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_medicamento", nullable = false)
    @Getter @Setter
    private Tipo_medicamento tipoMedicamento;

    @Column(nullable = false)
    @Getter @Setter
    private BigDecimal precio;

    @Column(nullable = false)
    @Getter @Setter
    private Integer estado;

    @Column(nullable = false)
    @Getter @Setter
    private LocalDate fechaExpiracion;

    @Column(nullable = false)
    @Getter @Setter
    private Integer stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proveedor", nullable = false)
    @Getter @Setter
    private Proveedor proveedor;

    @ManyToMany(mappedBy = "listadoMedicamentos", fetch = FetchType.LAZY)
    @Getter @Setter
    private List<Cita> listadoCitas;

    @OneToMany(mappedBy = "medicamento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter @Setter
    private List<Kardex_medicamento> listadoKardex;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "prescripcion_medica",
            joinColumns = @JoinColumn(name = "id_cita"),
            inverseJoinColumns = @JoinColumn(name = "id_medicamento")
    )
    @Getter @Setter
    private ArrayList<Medicamento> listadoMedicamentos;
}
