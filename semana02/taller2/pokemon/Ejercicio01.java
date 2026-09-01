package taller2.pokemon;

import taller2.model.Pokemon;
import java.util.List;

/**
 * Reto #01: Pokémon Tipo Fuego
 * Operación Stream: filter() + map() [Method Reference]
 * 
 * Enunciado: Dada una lista de Pokémon con nombre y tipo, obtener únicamente aquellos cuyo tipo sea Fuego.
 */
public class Ejercicio01 {
    public static void main(String[] args) {
        List<Pokemon> pokemones = List.of(
            new Pokemon("Pikachu", "Eléctrico"),
            new Pokemon("Charmander", "Fuego"),
            new Pokemon("Squirtle", "Agua"),
            new Pokemon("Vulpix", "Fuego"),
            new Pokemon("Bulbasaur", "Planta"),
            new Pokemon("Flareon", "Fuego")
        );

        List<String> tipoFuego = pokemones.stream()
                .filter(p -> "Fuego".equalsIgnoreCase(p.getTipo()))
                .map(Pokemon::getNombre)
                .toList();

        System.out.println("=== Reto #01: Pokémon Tipo Fuego ===");
        System.out.println(tipoFuego);
    }
}
