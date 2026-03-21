package com.example.Sistema_Clinica_Spring.Estructuras;

public class Nodo {
    Object dato;
    int prioridad;
    Nodo siguiente;

    public Nodo(Object dato, int prioridad) {
        this.dato = dato;
        this.prioridad = prioridad;
        this.siguiente = null;
    }
}
