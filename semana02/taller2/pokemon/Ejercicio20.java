package taller2.pokemon;

import taller2.model.Pokemon;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Reto #20: Pokédex Analítica
 * Operaciones Stream: groupingBy() + counting(), filter() + count(), mapToInt() + average(), max()
 * 
 * Enunciado: Construir una estructura que muestre:
 * - Cantidad de Pokémon por tipo
 * - Cantidad de Pokémon por región
 * - Cantidad de legendarios
 * - Promedio de nivel
 * - Pokémon más fuerte
 * Todo usando únicamente Streams.
 */
public class Ejercicio20 {
    public static void main(String[] args) {
        List<Pokemon> pokedex = List.of(
            new Pokemon(1L, "Charizard", "Fuego", 60, 610.0, "Kanto", false),
            new Pokemon(2L, "Arcanine", "Fuego", 55, 580.0, "Kanto", false),
            new Pokemon(3L, "Magmar", "Fuego", 50, 490.0, "Kanto", false),
            new Pokemon(4L, "Typhlosion", "Fuego", 58, 590.0, "Johto", false),
            new Pokemon(5L, "Blastoise", "Agua", 62, 600.0, "Kanto", false),
            new Pokemon(6L, "Feraligatr", "Agua", 58, 570.0, "Johto", false),
            new Pokemon(7L, "Gyarados", "Agua", 56, 560.0, "Kanto", false),
            new Pokemon(8L, "Mewtwo", "Psíquico", 75, 680.0, "Kanto", true),
            new Pokemon(9L, "Lugia", "Psíquico", 70, 670.0, "Johto", true),
            new Pokemon(10L, "Rayquaza", "Dragón", 70, 675.0, "Hoenn", true)
        );

        // 1. Cantidad de Pokémon por tipo
        Map<String, Long> porTipo = pokedex.stream()
                .collect(Collectors.groupingBy(Pokemon::getTipo, Collectors.counting()));

        // 2. Cantidad de Pokémon por región
        Map<String, Long> porRegion = pokedex.stream()
                .collect(Collectors.groupingBy(Pokemon::getRegion, Collectors.counting()));

        // 3. Cantidad de legendarios
        long legendarios = pokedex.stream()
                .filter(Pokemon::isLegendario)
                .count();

        // 4. Promedio de nivel
        double promedioNivel = pokedex.stream()
                .mapToInt(Pokemon::getNivel)
                .average()
                .orElse(0.0);

        // 5. Pokémon más fuerte
        Pokemon masFuerte = pokedex.stream()
                .max(Comparator.comparingDouble(Pokemon::getPoderCombate))
                .orElse(null);

        System.out.println("=== Reto #20: Pokédex Analítica ===");
        System.out.println("Por tipo: " + porTipo);
        System.out.println("Por región: " + porRegion);
        System.out.println("Legendarios: " + legendarios);
        System.out.printf(Locale.US, "Promedio niv: %.1f%n", promedioNivel);
        if (masFuerte != null) {
            System.out.println("Más fuerte: " + masFuerte.getNombre() + " (PC: " + (int) masFuerte.getPoderCombate() + ")");
        }
    }
}
