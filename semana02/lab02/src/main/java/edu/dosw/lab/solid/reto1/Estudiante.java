package edu.dosw.lab.solid.reto1;

public class Estudiante extends Espectador {
    public Estudiante() { super("Estudiante"); }
    @Override
    public double calcularDescuento(double subtotal) { return subtotal * 0.15; }
}
