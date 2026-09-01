package taller2.pokemon;

import java.util.List;

/**
 * Reto #06: Pokédex Sin Duplicados
 * Operación Stream: distinct()
 * 
 * Enunciado: Dada una lista de Pokémon con elementos repetidos, generar una nueva colección donde cada Pokémon aparezca una sola vez.
 */
public class Ejercicio06 {
    public static void main(String[] args) {
        List<String> pokemonesConDuplicados = List.of(
            "Pikachu", "Charmander", "Pikachu",
            "Squirtle", "Charmander", "Mewtwo"
        );

        List<String> sinDuplicados = pokemonesConDuplicados.stream()
                .distinct()
                .toList();

        System.out.println("=== Reto #06: Pokédex Sin Duplicados ===");
        System.out.println(sinDuplicados);
    }
}
