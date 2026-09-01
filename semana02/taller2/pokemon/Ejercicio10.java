package taller2.pokemon;

import taller2.model.Pokemon;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Reto #10: Pokédex Compacta
 * Operación Stream: map() + collect() con Method Reference (Pokemon::getNombre)
 * 
 * Enunciado: Generar una lista que contenga únicamente los nombres de todos los Pokémon del equipo.
 */
public class Ejercicio10 {
    public static void main(String[] args) {
        List<Pokemon> equipo = List.of(
            new Pokemon(1L, "Pikachu", "Eléctrico", 35, 320.0, "Kanto", false),
            new Pokemon(2L, "Mewtwo", "Psíquico", 70, 680.0, "Kanto", true),
            new Pokemon(3L, "Dragonite", "Dragón", 55, 530.0, "Kanto", false),
            new Pokemon(4L, "Squirtle", "Agua", 25, 210.0, "Kanto", false),
            new Pokemon(5L, "Gengar", "Fantasma", 50, 495.0, "Kanto", false),
            new Pokemon(6L, "Charizard", "Fuego", 60, 610.0, "Kanto", false)
        );

        List<String> nombres = equipo.stream()
                .map(Pokemon::getNombre)
                .collect(Collectors.toList());

        System.out.println("=== Reto #10: Pokédex Compacta ===");
        System.out.println(nombres);
    }
}
