package com.example.Sistema_Clinica_Spring.Models.Transacciones;

import com.example.Sistema_Clinica_Spring.Models.InventarioMedicamentos.Medicamento;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "venta")
@Data
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long idVenta;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_transaccion", nullable = false)
    @Getter @Setter
    private Transaccion transaccion;

    @Column(nullable = false)
    @Getter @Setter
    private BigDecimal montoTotal;

    @Column(nullable = false)
    @Getter @Setter
    private BigDecimal igv;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "detalle_venta",
            joinColumns = @JoinColumn(name = "id_venta"),
            inverseJoinColumns = @JoinColumn(name = "id_medicamento")
    )
    @Getter @Setter
    private List<Medicamento> listadoMedicamentosVentas;
}
