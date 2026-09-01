package edu.dosw.lab.solid.reto1;

public class TerceraEdad extends Espectador {
    public TerceraEdad() { super("Tercera edad"); }
    @Override
    public double calcularDescuento(double subtotal) { return subtotal * 0.25; }

}
