package taller2.pokemon;

import taller2.model.Pokemon;
import java.util.List;
import java.util.Locale;

/**
 * Reto #11: Poder Promedio
 * Operación Stream: mapToDouble() + average() con Method Reference (Pokemon::getPoderCombate)
 * 
 * Enunciado: Calcular el promedio de poderCombate de todos los Pokémon del equipo.
 */
public class Ejercicio11 {
    public static void main(String[] args) {
        List<Pokemon> equipo = List.of(
            new Pokemon("Pikachu", 320.0),
            new Pokemon("Mewtwo", 680.0),
            new Pokemon("Dragonite", 530.0),
            new Pokemon("Squirtle", 210.0),
            new Pokemon("Gengar", 495.0),
            new Pokemon("Charizard", 610.0)
        );

        double promedio = equipo.stream()
                .mapToDouble(Pokemon::getPoderCombate)
                .average()
                .orElse(0.0);

        System.out.printf(Locale.US, "Poder de combate promedio: %.2f%n", promedio);
    }
}
