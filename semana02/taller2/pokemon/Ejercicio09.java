package taller2.pokemon;

import taller2.model.Pokemon;
import java.util.List;

/**
 * Reto #09: Equipo Élite
 * Operación Stream: filter()
 * 
 * Enunciado: Mostrar únicamente los Pokémon cuyo poderCombate sea superior a 500.
 */
public class Ejercicio09 {
    public static void main(String[] args) {
        List<Pokemon> equipo = List.of(
            new Pokemon(1L, "Pikachu", "Eléctrico", 35, 320.0, "Kanto", false),
            new Pokemon(2L, "Mewtwo", "Psíquico", 70, 680.0, "Kanto", true),
            new Pokemon(3L, "Dragonite", "Dragón", 55, 530.0, "Kanto", false),
            new Pokemon(4L, "Squirtle", "Agua", 25, 210.0, "Kanto", false),
            new Pokemon(5L, "Gengar", "Fantasma", 50, 495.0, "Kanto", false),
            new Pokemon(6L, "Charizard", "Fuego", 60, 610.0, "Kanto", false)
        );

        List<String> elite = equipo.stream()
                .filter(p -> p.getPoderCombate() > 500)
                .map(p -> p.getNombre() + "(" + (int) p.getPoderCombate() + ")")
                .toList();

        System.out.println("Equipo Élite (PC > 500):");
        System.out.println(elite);
    }
}
