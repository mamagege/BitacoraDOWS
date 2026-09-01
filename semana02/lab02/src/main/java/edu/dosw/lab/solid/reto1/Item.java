package edu.dosw.lab.solid.reto1;

public class Item {
    private final String nombre;
    private final int precio;

    public Item(String nombre, int precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() { return nombre; }
    public int getPrecio() { return precio; }
}