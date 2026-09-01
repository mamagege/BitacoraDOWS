package taller2.pokemon;

import taller2.model.Pokemon;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Reto #18: Top 5 Pokémon Más Fuertes
 * Operación Stream: sorted() + limit(5) con Comparator.reversed()
 * 
 * Enunciado: Generar un ranking de los cinco Pokémon con mayor poderCombate de toda la Pokédex.
 */
public class Ejercicio18 {
    public static void main(String[] args) {
        List<Pokemon> pokedex = List.of(
            new Pokemon("Pikachu", 320.0),
            new Pokemon("Mewtwo", 680.0),
            new Pokemon("Dragonite", 530.0),
            new Pokemon("Squirtle", 210.0),
            new Pokemon("Gengar", 495.0),
            new Pokemon("Charizard", 610.0),
            new Pokemon("Bulbasaur", 190.0)
        );

        System.out.println("=== Reto #18: Top 5 Pokémon Más Fuertes ===");
        AtomicInteger ranking = new AtomicInteger(1);
        pokedex.stream()
                .sorted(Comparator.comparingDouble(Pokemon::getPoderCombate).reversed())
                .limit(5)
                .forEach(p -> System.out.println("#" + ranking.getAndIncrement() + " " + p.getNombre() + " – PC: " + (int) p.getPoderCombate()));
    }
}
