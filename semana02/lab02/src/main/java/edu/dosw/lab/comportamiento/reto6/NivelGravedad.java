package edu.dosw.lab.comportamiento.reto6;

import java.util.Arrays;

/**
 * Enumeración que representa los niveles de gravedad de los pacientes.
 */
public enum NivelGravedad {
    LEVE("Leve"),
    MODERADO("Moderado"),
    GRAVE("Grave"),
    CRITICO("Crítico");

    private final String descripcion;

    NivelGravedad(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static NivelGravedad desdeTexto(String texto) {
        if (texto == null || texto.isBlank()) return LEVE;
        String t = texto.trim().toUpperCase().replace("Í", "I");
        return Arrays.stream(values())
                .filter(n -> n.name().equals(t) || n.descripcion.toUpperCase().replace("Í", "I").equals(t))
                .findFirst()
                .orElse(LEVE);
    }
}
