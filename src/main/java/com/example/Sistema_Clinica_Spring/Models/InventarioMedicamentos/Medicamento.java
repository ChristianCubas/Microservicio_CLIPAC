package com.example.Sistema_Clinica_Spring.Models.InventarioMedicamentos;

import com.example.Sistema_Clinica_Spring.Models.Transacciones.Complemento.Diagnostico;
import com.example.Sistema_Clinica_Spring.Models.Transacciones.Venta;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "medicamento")
@Getter
@Setter
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medicamento")
    private Long id_medicamento;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_medicamento", nullable = false)
    private Tipo_medicamento tipoMedicamento;

    @Column(name = "precio", nullable = false)
    private BigDecimal precio;

    @Column(name = "stock_unidades", nullable = false)
    private int stock_unidades;

    @Column(name = "estado", nullable = false)
    private Integer estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proveedor", nullable = false)
    private Proveedor proveedor;

    @Column(name = "fecha_plazo")
    private LocalDate fecha_plazo;

    @OneToMany(mappedBy = "medicamento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Kardex_medicamento> listadoKardex = new ArrayList<>();

    @ManyToMany(mappedBy = "listadoMedicamentos", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Diagnostico> diagnosticos = new ArrayList<>();

    @ManyToMany(mappedBy = "listadoMedicamentosVentas", fetch = FetchType.LAZY)
    private List<Venta> ventas = new ArrayList<>();

    // Método auxiliar para calcular stock total
    public Integer getStockTotal() {
        return listadoKardex.stream()
                .filter(k -> k.getTipoMovimiento().equals("entrada"))
                .mapToInt(Kardex_medicamento::getCantidad)
                .sum() -
                listadoKardex.stream()
                        .filter(k -> k.getTipoMovimiento().equals("salida"))
                        .mapToInt(Kardex_medicamento::getCantidad)
                        .sum();
    }
}