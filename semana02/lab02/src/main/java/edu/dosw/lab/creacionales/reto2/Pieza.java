package edu.dosw.lab.creacionales.reto2;

public class Pieza {
    private final String nombre;
    private final double precio;

    public Pieza(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
}