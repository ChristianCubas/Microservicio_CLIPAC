package com.example.Sistema_Clinica_Spring.Models.InventarioMedicamentos;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "proveedor")
@Data
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    @Getter @Setter
    private Long id_proveedor;

    @Column(name = "razonSocial",nullable = false)
    @Getter @Setter
    private String razonSocial;

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
    private List<Medicamento> listadoMedicamentos;
}
