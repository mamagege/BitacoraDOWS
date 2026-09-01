package edu.dosw.lab.comportamiento.reto4;

public class Libra implements EstrategiaUnidad {
    @Override
    public double aKilos(double cantidad) {
        return cantidad / 2.2046;
    }

    @Override
    public double desdeKilos(double kilos) {
        return kilos * 2.2046;
    }

    @Override
    public String getSimbolo() {
        return "lb";
    }
}