package edu.dosw.lab.comportamiento.reto4;

public class Kilogramo implements EstrategiaUnidad {
    @Override
    public double aKilos(double cantidad) {
        return cantidad;
    }

    @Override
    public double desdeKilos(double kilos) {
        return kilos;
    }

    @Override
    public String getSimbolo() {
        return "kg";
    }
}