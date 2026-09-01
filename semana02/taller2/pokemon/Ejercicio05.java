package taller2.pokemon;

import taller2.model.Pokemon;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Reto #05: Pokémon Legendarios
 * Operación Stream: filter() + count() + map() [Method Reference]
 * 
 * Enunciado: Contar cuántos Pokémon del equipo tienen nivel superior a 80 y listar sus nombres.
 */
public class Ejercicio05 {
    public static void main(String[] args) {
        List<Pokemon> equipo = List.of(
            new Pokemon("Pikachu", 45),
            new Pokemon("Mewtwo", 88),
            new Pokemon("Dragonite", 82),
            new Pokemon("Squirtle", 38),
            new Pokemon("Mew", 85),
            new Pokemon("Charmander", 62)
        );

        List<Pokemon> legendarios = equipo.stream()
                .filter(p -> p.getNivel() > 80)
                .toList();

        long cantidad = legendarios.stream().count();
        String nombres = legendarios.stream()
                .map(Pokemon::getNombre)
                .collect(Collectors.joining(", "));

        System.out.println("Pokémon con nivel > 80: " + cantidad);
        System.out.println("(" + nombres + ")");
    }
}
