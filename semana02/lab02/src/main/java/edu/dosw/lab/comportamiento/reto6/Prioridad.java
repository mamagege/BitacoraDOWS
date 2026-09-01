package edu.dosw.lab.comportamiento.reto6;

import java.util.Arrays;

/**
 * Enumeración que representa la prioridad de atención con su peso numérico.
 */
public enum Prioridad {
    BAJA("Baja", 1),
    MEDIA("Media", 2),
    ALTA("Alta", 3);

    private final String etiqueta;
    private final int valor;

    Prioridad(String etiqueta, int valor) {
        this.etiqueta = etiqueta;
        this.valor = valor;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public int getValor() {
        return valor;
    }

    public static Prioridad desdeTexto(String texto) {
        if (texto == null || texto.isBlank()) return BAJA;
        String t = texto.trim().toUpperCase();
        return Arrays.stream(values())
                .filter(p -> p.name().equals(t) || p.etiqueta.toUpperCase().equals(t) || String.valueOf(p.valor).equals(t))
                .findFirst()
                .orElse(BAJA);
    }
}
