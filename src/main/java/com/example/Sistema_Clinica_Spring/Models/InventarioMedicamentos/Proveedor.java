package com.example.Sistema_Clinica_Spring.Models.InventarioMedicamentos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "proveedor")
@Getter
@Setter
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    @Getter @Setter
    private Long id_proveedor;

    @Column(name = "razon_social",nullable = false)
    @Getter @Setter
    private String razon_social;

    @Column(name = "nroRuc",nullable = false)
    @Getter @Setter
    private String nroRuc;

    @Column(name = "telefono",nullable = false)
    @Getter @Setter
    private String telefono;

    @Column(name = "correo",nullable = false)
    @Getter @Setter
    private String correo;

    @Column(name = "direccion",nullable = false)
    @Getter @Setter
    private String direccion;

    @Column(nullable = false)
    private String cuentaBancaria;

    @Column(name = "estado", nullable = false)
    private Integer estado;

    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Medicamento> listadoMedicamentos;
}
