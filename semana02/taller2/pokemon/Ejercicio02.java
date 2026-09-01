package taller2.pokemon;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Reto #02: Pokédex Gritona
 * Operación Stream: map() con Method Reference (String::toUpperCase)
 * 
 * Enunciado: Transformar todos los nombres de Pokémon a mayúsculas.
 */
public class Ejercicio02 {
    public static void main(String[] args) {
        List<String> pokemones = List.of("Pikachu", "Charmander", "Squirtle", "Bulbasaur");

        String resultado = pokemones.stream()
                .map(String::toUpperCase)
                .collect(Collectors.joining(", "));

        System.out.println("=== Reto #02: Pokédex Gritona ===");
        System.out.println(resultado);
    }
}
