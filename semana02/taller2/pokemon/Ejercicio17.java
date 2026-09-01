package taller2.pokemon;

import taller2.model.Entrenador;
import taller2.model.Pokemon;
import java.util.Comparator;
import java.util.List;

/**
 * Reto #17: Equipo Más Poderoso
 * Operación Stream: mapToDouble() + sum() + max() con Comparator
 * 
 * Enunciado: Calcular cuál entrenador tiene la suma total de poderCombate más alta entre todos sus Pokémon.
 */
public class Ejercicio17 {
    public static void main(String[] args) {
        // Ash: PC Total = 1850 (ej. 600 + 650 + 600)
        Entrenador ash = new Entrenador("Ash", 8, List.of(
            new Pokemon("Charizard", 650.0),
            new Pokemon("Pikachu", 600.0),
            new Pokemon("Snorlax", 600.0)
        ));

        // Gary: PC Total = 2340 (ej. 800 + 780 + 760)
        Entrenador gary = new Entrenador("Gary", 10, List.of(
            new Pokemon("Blastoise", 800.0),
            new Pokemon("Arcanine", 780.0),
            new Pokemon("Electivire", 760.0)
        ));

        // Brock: PC Total = 1670 (ej. 570 + 550 + 550)
        Entrenador brock = new Entrenador("Brock", 6, List.of(
            new Pokemon("Onix", 570.0),
            new Pokemon("Geodude", 550.0),
            new Pokemon("Steelix", 550.0)
        ));

        List<Entrenador> entrenadores = List.of(ash, gary, brock);

        entrenadores.stream()
                .max(Comparator.comparingDouble(e -> e.getEquipo().stream()
                        .mapToDouble(Pokemon::getPoderCombate)
                        .sum()))
                .ifPresent(poderoso -> {
                    double pcTotal = poderoso.getEquipo().stream()
                            .mapToDouble(Pokemon::getPoderCombate)
                            .sum();
                    System.out.println("Entrenador más poderoso: " + poderoso.getNombre());
                    System.out.println("Poder acumulado del equipo: " + (int) pcTotal);
                });
    }
}
