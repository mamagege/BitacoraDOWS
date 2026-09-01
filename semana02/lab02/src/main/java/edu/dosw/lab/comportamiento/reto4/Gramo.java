package edu.dosw.lab.comportamiento.reto4;

public class Gramo implements EstrategiaUnidad {
    @Override
    public double aKilos(double cantidad) {
        return cantidad / 1000.0;
    }

    @Override
    public double desdeKilos(double kilos) {
        return kilos * 1000.0;
    }

    @Override
    public String getSimbolo() {
        return "g";
    }
}