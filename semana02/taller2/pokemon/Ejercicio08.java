package taller2.pokemon;

import taller2.model.Pokemon;
import java.util.List;

/**
 * Reto #08: Evoluciones Preparadas
 * Operación Stream: filter() con Method Reference (Pokemon::isPuedeEvolucionar)
 * 
 * Enunciado: Dada una lista de Pokémon que incluye si pueden evolucionar (boolean puedeEvolucionar),
 * obtener únicamente los que estén listos para evolucionar.
 */
public class Ejercicio08 {
    public static void main(String[] args) {
        List<Pokemon> pokemones = List.of(
            new Pokemon("Pikachu", true),
            new Pokemon("Raichu", false),
            new Pokemon("Charmander", true),
            new Pokemon("Charizard", false),
            new Pokemon("Squirtle", true),
            new Pokemon("Blastoise", false)
        );

        List<String> listosParaEvolucionar = pokemones.stream()
                .filter(Pokemon::isPuedeEvolucionar)
                .map(Pokemon::getNombre)
                .toList();

        System.out.println("Listos para evolucionar:");
        System.out.println(listosParaEvolucionar);
    }
}
