package com.example.Sistema_Clinica_Spring.DTO;

import java.math.BigDecimal;

public class MedicamentoDTO {
    private Long idMedicamento;
    private String nombre;
    private BigDecimal precio;

    // constructor, getters y setters

    public MedicamentoDTO(Long idMedicamento, String nombre, BigDecimal precio) {
        this.idMedicamento = idMedicamento;
        this.nombre = nombre;
        this.precio = precio;
    }

    public Long getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(Long idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
}
