package edu.dosw.lab.comportamiento.reto4;

public class Pesaje {
    private final double cantidadOriginal;
    private final EstrategiaUnidad origen;
    private final EstrategiaUnidad destino;

    public Pesaje(double cantidadOriginal, EstrategiaUnidad origen, EstrategiaUnidad destino) {
        this.cantidadOriginal = cantidadOriginal;
        this.origen = origen;
        this.destino = destino;
    }

    public double calcularResultado() {
        double equivalenciaEnKilos = origen.aKilos(cantidadOriginal);
        return destino.desdeKilos(equivalenciaEnKilos);
    }

    public double obtenerKilosBase() {
        return origen.aKilos(cantidadOriginal);
    }

    public double getCantidadOriginal() {
        return cantidadOriginal;
    }

    public EstrategiaUnidad getOrigen() {
        return origen;
    }

    public EstrategiaUnidad getDestino() {
        return destino;
    }
}