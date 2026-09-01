package edu.dosw.lab.creacionales.reto3;

public class CatalogoPrecio {
    public static double obtenerPrecioBase(String modelo) {
        String mod = modelo.toLowerCase().trim();
        if (mod.contains("guitarra")) return 800000.0;
        if (mod.contains("viol")) return 1600000.0;
        if (mod.contains("bajo")) return 1200000.0;
        if (mod.contains("saxof")) return 2500000.0;
        if (mod.contains("flauta")) return 700000.0;
        if (mod.contains("trompeta")) return 1500000.0;
        if (mod.contains("bater")) return 1800000.0;
        if (mod.contains("caj")) return 350000.0;
        if (mod.contains("timbal")) return 600000.0;
        return 1000000.0;
    }

    public static String capitalizar(String texto) {
        if (texto == null || texto.isEmpty()) return texto;
        return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
    }
}