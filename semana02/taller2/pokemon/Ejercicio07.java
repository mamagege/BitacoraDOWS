package taller2.pokemon;

import java.util.List;

/**
 * Reto #07: Orden del Profesor Oak
 * Operación Stream: sorted()
 * 
 * Enunciado: Ordenar alfabéticamente los nombres de los Pokémon.
 */
public class Ejercicio07 {
    public static void main(String[] args) {
        List<String> pokemones = List.of(
            "Squirtle", "Pikachu", "Mewtwo",
            "Bulbasaur", "Charmander", "Abra"
        );

        List<String> ordenados = pokemones.stream()
                .sorted()
                .toList();

        System.out.println("=== Reto #07: Orden del Profesor Oak ===");
        System.out.println(ordenados);
    }
}
