package edu.dosw.lab.creacionales.reto3;

public class Instrumento {
    private final String modelo;
    private final double precioBase;
    private final String gama;
    private final String afinacion;
    private final double factorPrecio;

    public Instrumento(String modelo, double precioBase, String gama, String afinacion, double factorPrecio) {
        this.modelo = modelo;
        this.precioBase = precioBase;
        this.gama = gama;
        this.afinacion = afinacion;
        this.factorPrecio = factorPrecio;
    }

    public double calcularPrecioFinal() {
        return precioBase * factorPrecio;
    }

    public String getNombreCompleto() {
        return modelo + " " + gama;
    }

    public String getAfinacion() {
        return afinacion;
    }
}