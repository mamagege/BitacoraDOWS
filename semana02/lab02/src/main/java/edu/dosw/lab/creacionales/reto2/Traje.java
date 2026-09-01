package edu.dosw.lab.creacionales.reto2;

import java.util.*;

public class Traje {
    private final List<Pieza> piezas;

    protected Traje(List<Pieza> piezas) {
        this.piezas = new ArrayList<>(piezas);
    }

    public List<Pieza> getPiezas() {
        return Collections.unmodifiableList(piezas);
    }

    public double calcularPrecioTotal() {
        return piezas.stream().mapToDouble(Pieza::getPrecio).sum();
    }
}