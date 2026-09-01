package taller2.pokemon;

import taller2.model.Entrenador;
import java.util.Comparator;
import java.util.List;

/**
 * Reto #15: Maestro de Gimnasios
 * Operación Stream: max() con Comparator y Method Reference (Entrenador::getMedallas)
 * 
 * Enunciado: Dado un listado de entrenadores con sus medallas, encontrar el entrenador con más medallas.
 */
public class Ejercicio15 {
    public static void main(String[] args) {
        List<Entrenador> entrenadores = List.of(
            new Entrenador("Ash", 8),
            new Entrenador("Misty", 5),
            new Entrenador("Brock", 6),
            new Entrenador("Gary", 10)
        );

        entrenadores.stream()
                .max(Comparator.comparingInt(Entrenador::getMedallas))
                .ifPresent(campeon -> {
                    System.out.println("Campeón de gimnasios: " + campeon.getNombre());
                    System.out.println("Medallas obtenidas: " + campeon.getMedallas());
                });
    }
}
