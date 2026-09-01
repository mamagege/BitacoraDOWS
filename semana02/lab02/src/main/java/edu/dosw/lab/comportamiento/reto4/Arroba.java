package edu.dosw.lab.comportamiento.reto4;

public class Arroba implements EstrategiaUnidad {
    @Override
    public double aKilos(double cantidad) {
        return cantidad / 0.08;
    }

    @Override
    public double desdeKilos(double kilos) {
        return kilos * 0.08;
    }

    @Override
    public String getSimbolo() {
        return "@";
    }
}