package taller2.pokemon;

import taller2.model.Pokemon;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Reto #14: Organizar por Región
 * Operación Stream: groupingBy() + mapping() con Method References
 * 
 * Enunciado: Agrupar los Pokémon según su región de origen.
 */
public class Ejercicio14 {
    public static void main(String[] args) {
        List<Pokemon> pokemones = List.of(
            new Pokemon(1L, "Pikachu", "Eléctrico", 35, 320.0, "Kanto", false),
            new Pokemon(2L, "Chikorita", "Planta", 15, 150.0, "Johto", false),
            new Pokemon(3L, "Torchic", "Fuego", 16, 160.0, "Hoenn", false),
            new Pokemon(4L, "Piplup", "Agua", 15, 155.0, "Sinnoh", false),
            new Pokemon(5L, "Charmander", "Fuego", 20, 200.0, "Kanto", false),
            new Pokemon(6L, "Totodile", "Agua", 18, 170.0, "Johto", false)
        );

        Map<String, List<String>> agrupadosPorRegion = pokemones.stream()
                .collect(Collectors.groupingBy(
                        Pokemon::getRegion,
                        LinkedHashMap::new,
                        Collectors.mapping(Pokemon::getNombre, Collectors.toList())
                ));

        System.out.println("=== Reto #14: Organizar por Región ===");
        agrupadosPorRegion.forEach((region, listaNombres) -> 
            System.out.println(region + ": " + listaNombres)
        );
    }
}
