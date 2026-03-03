package com.example.Sistema_Clinica_Spring.Models.Transacciones;

import com.example.Sistema_Clinica_Spring.Models.Transacciones.Complemento.Tipo_examen;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "examen")
@Data
public class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long idExamen;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_transaccion", nullable = false)
    @Getter @Setter
    private Transaccion transaccion;

    @Column(nullable = false)
    @Getter @Setter
    private String titulo;

    @Column(nullable = false)
    @Getter @Setter
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_examen", nullable = false)
    @Getter @Setter
    private Tipo_examen tipoExamen;
}
