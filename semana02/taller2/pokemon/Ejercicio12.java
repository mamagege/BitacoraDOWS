package taller2.pokemon;

import taller2.model.Pokemon;
import java.util.Comparator;
import java.util.List;

/**
 * Reto #12: Campeón Regional
 * Operación Stream: max() con Comparator y Method Reference (Pokemon::getPoderCombate)
 * 
 * Enunciado: Obtener el Pokémon con mayor poderCombate de toda la lista.
 */
public class Ejercicio12 {
    public static void main(String[] args) {
        List<Pokemon> lista = List.of(
            new Pokemon("Pikachu", 320.0),
            new Pokemon("Mewtwo", 680.0),
            new Pokemon("Dragonite", 530.0),
            new Pokemon("Charizard", 610.0)
        );

        lista.stream()
                .max(Comparator.comparingDouble(Pokemon::getPoderCombate))
                .ifPresent(campeon -> System.out.println("Campeón: " + campeon.getNombre() + " con PC: " + (int) campeon.getPoderCombate()));
    }
}
