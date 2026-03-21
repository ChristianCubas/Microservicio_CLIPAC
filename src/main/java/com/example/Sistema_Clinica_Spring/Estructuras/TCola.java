package com.example.Sistema_Clinica_Spring.Estructuras;

import com.example.Sistema_Clinica_Spring.Models.InventarioMedicamentos.Kardex_medicamento;

import java.util.ArrayList;
import java.util.List;

public class TCola {
    private Nodo inicio;

    public TCola() {
        inicio = null;
    }

    // Insertar manteniendo orden por prioridad (mayor prioridad primero)
    public void insertar(Object dato, int prioridad) {
        Nodo nuevo = new Nodo(dato, prioridad);

        if (inicio == null || prioridad > inicio.prioridad) {
            nuevo.siguiente = inicio;
            inicio = nuevo;
        } else {
            Nodo actual = inicio;

            while (actual.siguiente != null &&
                    actual.siguiente.prioridad >= prioridad) {
                actual = actual.siguiente;
            }

            nuevo.siguiente = actual.siguiente;
            actual.siguiente = nuevo;
        }
    }

    // Eliminar el elemento de mayor prioridad
    public Object eliminar() {
        if (inicio == null) {
            throw new RuntimeException("La cola está vacía");
        }

        Object dato = inicio.dato;
        inicio = inicio.siguiente;
        return dato;
    }

    // Ver el primer elemento
    public Object frente() {
        if (inicio == null) {
            throw new RuntimeException("La cola está vacía");
        }
        return inicio.dato;
    }

    // Verificar si está vacía
    public boolean estaVacia() {
        return inicio == null;
    }

    // Mostrar cola
    public void mostrar() {
        Nodo actual = inicio;

        while (actual != null) {
            System.out.println("Dato: " + actual.dato +
                    " | Prioridad: " + actual.prioridad);
            actual = actual.siguiente;
        }
    }

    public List<Object> convertList(){
        List<Object> lista = new ArrayList<>();

        Nodo actual = inicio;

        while(actual != null){
            lista.add(actual.dato);
            actual = actual.siguiente;
        }

        return lista;
    }

    public List<Kardex_medicamento> toList(){
        List<Kardex_medicamento> lista = new ArrayList<>();

        Nodo actual = inicio;

        while(actual != null){
            lista.add((Kardex_medicamento) actual.dato);
            actual = actual.siguiente;
        }

        return lista;
    }
}
