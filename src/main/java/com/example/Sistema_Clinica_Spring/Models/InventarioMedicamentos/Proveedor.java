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
    @Getter @Setter
    private Long idProveedor;

    @Column(nullable = false)
    @Getter @Setter
    private String razonSocial;

    @Column(nullable = false)
    @Getter @Setter
    private String nroRuc;

    @Column(nullable = false)
    @Getter @Setter
    private String telefono;

    @Column(nullable = false)
    @Getter @Setter
    private String correo;

    @Column(nullable = false)
    @Getter @Setter
    private String direccion;

    @Column(nullable = false)
    private String cuentaBancaria;

    @Column(nullable = false)
    private Integer estado;

    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Medicamento> listadoMedicamentos;
}
