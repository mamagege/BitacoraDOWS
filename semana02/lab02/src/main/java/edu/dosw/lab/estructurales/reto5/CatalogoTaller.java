package edu.dosw.lab.estructurales.reto5;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Catálogo centralizado de precios y especificaciones del Taller Turbo Andes.
 */
public class CatalogoTaller {

    public record OpcionMejora(int id, String tipo, String nombre, double precio) {}

    private static final Map<String, Double> PRECIOS_MOTOS = Map.of(
            "Naked 250", 9_800_000.0
    );

    private static final Map<Integer, OpcionMejora> MEJORAS_DISPONIBLES;

    static {
        Map<Integer, OpcionMejora> map = new LinkedHashMap<>();
        map.put(1, new OpcionMejora(1, "Accesorio", "Escape deportivo", 1_400_000.0));
        map.put(2, new OpcionMejora(2, "Pintura", "Pintura mate negro", 900_000.0));
        map.put(3, new OpcionMejora(3, "Complemento", "GPS integrado", 1_100_000.0));
        map.put(4, new OpcionMejora(4, "Complemento", "Baúl trasero", 550_000.0));
        map.put(5, new OpcionMejora(5, "Accesorio", "Manillar deportivo", 480_000.0));
        map.put(6, new OpcionMejora(6, "Accesorio", "Luces LED", 350_000.0));
        map.put(7, new OpcionMejora(7, "Accesorio", "Alforjas laterales", 600_000.0));
        map.put(8, new OpcionMejora(8, "Pintura", "Metalizado tricapa", 1_600_000.0));
        map.put(9, new OpcionMejora(9, "Pintura", "Vinilo personalizado", 700_000.0));
        map.put(10, new OpcionMejora(10, "Complemento", "Sistema de sonido", 820_000.0));
        MEJORAS_DISPONIBLES = Collections.unmodifiableMap(map);
    }

    public static double getPrecioMoto(String modelo) {
        return PRECIOS_MOTOS.getOrDefault(modelo, 0.0);
    }

    public static Optional<OpcionMejora> buscarMejoraPorId(int id) {
        return Optional.ofNullable(MEJORAS_DISPONIBLES.get(id));
    }

    public static double getPrecioMejoraPorNombre(String nombre) {
        return MEJORAS_DISPONIBLES.values().stream()
                .filter(m -> m.nombre().equalsIgnoreCase(nombre))
                .map(OpcionMejora::precio)
                .findFirst()
                .orElse(0.0);
    }

    public static Map<Integer, OpcionMejora> getMejorasDisponibles() {
        return MEJORAS_DISPONIBLES;
    }
}
