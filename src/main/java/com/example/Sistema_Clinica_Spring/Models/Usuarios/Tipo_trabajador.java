package com.example.Sistema_Clinica_Spring.Models.Usuarios;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tipo_trabajador")
@Data
public class Tipo_trabajador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_trabajador")
    @Getter
    @Setter
    private Long id_tipo_trabajador;

    @Column(nullable = false)
    @Getter @Setter
    private String nombre;

    @Column(nullable = false)
    @Getter @Setter
    private Integer estado;

    @OneToMany(mappedBy = "tipoTrabajador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter @Setter
    private List<Trabajador> listadoTrabajadores;
}
