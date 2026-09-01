package taller2.pokemon;

import taller2.model.Pokemon;
import java.util.Comparator;
import java.util.List;

/**
 * Reto #04: Pokémon Alfa
 * Operación Stream: max() con Comparator y Method Reference
 * 
 * Enunciado: Encontrar el Pokémon con el nivel más alto dentro del equipo.
 */
public class Ejercicio04 {
    public static void main(String[] args) {
        List<Pokemon> equipo = List.of(
            new Pokemon("Pikachu", 45),
            new Pokemon("Charmander", 62),
            new Pokemon("Squirtle", 38),
            new Pokemon("Snorlax", 90),
            new Pokemon("Mewtwo", 88)
        );

        equipo.stream()
                .max(Comparator.comparingInt(Pokemon::getNivel))
                .ifPresent(alfa -> System.out.println("Pokémon Alfa: " + alfa.getNombre() + " (nivel " + alfa.getNivel() + ")"));
    }
}
