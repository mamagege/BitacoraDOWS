package taller2.pokemon;

import taller2.model.Pokemon;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Reto #13: Organizar por Tipo
 * Operación Stream: groupingBy() + mapping() con Method References
 * 
 * Enunciado: Agrupar todos los Pokémon por su tipo y mostrar el listado por grupo.
 */
public class Ejercicio13 {
    public static void main(String[] args) {
        List<Pokemon> pokemones = List.of(
            new Pokemon("Squirtle", "Agua"),
            new Pokemon("Psyduck", "Agua"),
            new Pokemon("Charmander", "Fuego"),
            new Pokemon("Vulpix", "Fuego"),
            new Pokemon("Bulbasaur", "Planta")
        );

        Map<String, List<String>> agrupadosPorTipo = pokemones.stream()
                .collect(Collectors.groupingBy(
                        Pokemon::getTipo,
                        LinkedHashMap::new,
                        Collectors.mapping(Pokemon::getNombre, Collectors.toList())
                ));

        System.out.println("=== Reto #13: Organizar por Tipo ===");
        agrupadosPorTipo.forEach((tipo, listaNombres) -> 
            System.out.println(tipo + ": " + listaNombres)
        );
    }
}
