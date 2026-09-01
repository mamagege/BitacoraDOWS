package edu.dosw.lab.creacionales.reto3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pedido {
    private final List<Instrumento> instrumentos = new ArrayList<>();

    public void agregarInstrumento(Instrumento instrumento) {
        instrumentos.add(instrumento);
    }

    public List<Instrumento> getInstrumentos() {
        return Collections.unmodifiableList(instrumentos);
    }

    public double calcularTotal() {
        return instrumentos.stream()
                .mapToDouble(Instrumento::calcularPrecioFinal)
                .sum();
    }
}