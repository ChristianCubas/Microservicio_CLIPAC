package com.example.Sistema_Clinica_Spring.Models.InventarioMedicamentos;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tipo_medicamento")
@Data
public class Tipo_medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long idTipoMedicamento;

    @Column(nullable = false)
    @Getter @Setter
    private String nombre;

    @Column(nullable = false)
    @Getter @Setter
    private Integer estado;

    @OneToMany(mappedBy = "tipoMedicamento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter @Setter
    private List<Medicamento> listadoMedicamentos;
}
