package edu.dosw.lab.solid.reto1;

public abstract class Espectador {
    private final String tipo;

    public Espectador(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public abstract double calcularDescuento(double subtotal);
}





