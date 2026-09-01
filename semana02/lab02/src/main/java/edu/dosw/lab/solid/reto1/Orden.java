package edu.dosw.lab.solid.reto1;

import java.util.ArrayList;
import java.util.List;

public class Orden {
    private final List<Item> items = new ArrayList<>();
    private final Espectador espectador;

    public Orden(Espectador espectador) {
        this.espectador = espectador;
    }

    public void agregarItem(Item item, int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            items.add(item);
        }
    }

    public double calcularSubtotal() {
        return items.stream().mapToDouble(Item::getPrecio).reduce(0, Double::sum);
    }

    public double calcularDescuento() {
        return espectador.calcularDescuento(calcularSubtotal());
    }

    public double calcularTotal() {
        return calcularSubtotal() - calcularDescuento();
    }
    
    public List<Item> getItems() { return items; }
    public Espectador getEspectador() { return espectador; }
}